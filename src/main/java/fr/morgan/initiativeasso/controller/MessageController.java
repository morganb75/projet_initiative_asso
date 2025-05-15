package fr.morgan.initiativeasso.controller;

import fr.morgan.initiativeasso.model.Message;
import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.dto.MessageDto;
import fr.morgan.initiativeasso.model.dto.UserWsOnLineDto;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;
import fr.morgan.initiativeasso.service.PresenceService;
import fr.morgan.initiativeasso.service.interfaces.MessageService;
import fr.morgan.initiativeasso.service.interfaces.UserService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpSession;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MessageController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;
    private final UserService userService;
    private final PresenceService presenceService;
    private final SimpUserRegistry simpUserRegistry;

    public MessageController(SimpMessagingTemplate messagingTemplate, MessageService messageService, UserService userService,
            PresenceService presenceService,
            SimpUserRegistry simpUserRegistry) {
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
        this.userService = userService;
        this.presenceService = presenceService;
        this.simpUserRegistry = simpUserRegistry;
    }

    @MessageMapping("/msg.private")
    public void handlePrivateMessage(MessageDto messageDto, Principal principal) throws UserNotFoundException {
        String username = principal.getName();
        User sender = userService.findByEmail(username).orElseThrow(() -> new UserNotFoundException("user not found!"));
        User receiver = userService.findById(messageDto.getReceiverId()).orElseThrow(() -> new UserNotFoundException("user not found!"));
        messageDto.setSenderId(sender.getId());
        messageDto.setReceiverId(receiver.getId());
        messageDto.setSenderName(sender.getNom());
        messageDto.setSenderFirstName(sender.getPrenom());
        messageDto.setTime(LocalDateTime.now());

        // DEBUG: Affiche les utilisateurs et leurs sessions
        for (SimpUser user : simpUserRegistry.getUsers()) {
            log.info(" Utilisateur connecté : {}", user.getName());
            for (SimpSession session : user.getSessions()) {
                log.info("   ↳ Session : {}", session.getId());
            }
        }
        Message savedMessage = messageService.saveMessage(messageDto);
        log.info("✉️ Envoi message à {}", receiver.getEmail());
        simpUserRegistry.getUsers().forEach(user -> {
            log.info("👤 Utilisateur STOMP actif : {}", user.getName());
        });
        messagingTemplate.convertAndSendToUser(
                receiver.getEmail(),
                "/queue/messages",
                messageDto
        );
    }

    @MessageMapping("/presence/register")
    public void registerPresence(SimpMessageHeaderAccessor headerAccessor, Principal principal) throws UserNotFoundException {
        if (principal != null) {
            String sessionId = headerAccessor.getSessionId();
            presenceService.userConnected(sessionId, principal.getName());

            // Réenvoi immédiat de l'état de présence
            Collection<UserWsOnLineDto> onlineUsers = presenceService.getConnectedUsers();
            messagingTemplate.convertAndSendToUser(principal.getName(),"/queue/presence", onlineUsers);
        }
    }

    @GetMapping("/messages")
    public List<MessageDto> getMessageByReceiverId(@RequestParam Long userId) throws UserNotFoundException {
        return messageService.findMessagesByReceiverId(userId);
    }

    @GetMapping("/messages/conversations")
    public Map<Long, List<MessageDto>> getConversationByUserId(@RequestParam Long userId) {
        return messageService.getConversationByUserId(userId);
    }
}
