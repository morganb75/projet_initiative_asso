package fr.morgan.initiativeasso.controller;

import fr.morgan.initiativeasso.model.Message;
import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.dto.MessageDto;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;
import fr.morgan.initiativeasso.service.interfaces.MessageService;
import fr.morgan.initiativeasso.service.interfaces.UserService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpSession;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class MessageController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;
    private final UserService userService;
    private final SimpUserRegistry simpUserRegistry;

    public MessageController(SimpMessagingTemplate messagingTemplate, MessageService messageService, UserService userService,
            SimpUserRegistry simpUserRegistry) {
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
        this.userService = userService;
        this.simpUserRegistry = simpUserRegistry;
    }

    @MessageMapping("/msg.private")
    public void handlePrivateMessage(MessageDto messageDto, Principal principal) throws UserNotFoundException {
        String username = principal.getName();
        User sender = userService.findByEmail(username).orElseThrow(() -> new UserNotFoundException("user not found!"));
        User receiver = userService.findById(messageDto.getReceiverId()).orElseThrow(() -> new UserNotFoundException("user not found!"));
        messageDto.setSenderId(sender.getId());
        messageDto.setReceiverId(receiver.getId());
        messageDto.setTime(LocalDateTime.now());

        // DEBUG: Affiche les utilisateurs et leurs sessions
        for (SimpUser user : simpUserRegistry.getUsers()) {
            log.info(" Utilisateur connecté : {}", user.getName());
            for (SimpSession session : user.getSessions()) {
                log.info("   ↳ Session : {}", session.getId());
            }
        }

        //        Message savedMessage = messageService.saveMessage(messageDto);
        //        messageDto.setSenderId(sender.getId());
        //        String receiverUsername = savedMessage.getReceiver().getUsername();
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
}
