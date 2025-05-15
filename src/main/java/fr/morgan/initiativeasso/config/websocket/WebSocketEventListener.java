package fr.morgan.initiativeasso.config.websocket;

import fr.morgan.initiativeasso.model.dto.UserWsOnLineDto;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;
import fr.morgan.initiativeasso.service.PresenceService;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Slf4j
@Component
public class WebSocketEventListener {

    private final PresenceService presenceService;
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketEventListener(PresenceService presenceService, SimpMessagingTemplate messagingTemplate) {
        this.presenceService = presenceService;
        this.messagingTemplate = messagingTemplate;
    }

    //    @EventListener
    //    public void handleSessionConnectEvent(SessionConnectEvent event) {
    //        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
    //        Principal user = sha.getUser();
    //        log.info("🧾 Connecté STOMP : {}", user != null ? user.getName() : "anonyme");
    //    }

    @EventListener
    public void handleSessionConnected(SessionConnectedEvent event) throws UserNotFoundException {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        Principal user = event.getUser();
        String sessionId = accessor.getSessionId();
        if (user != null) {
            log.warn("👤 Connexion WebSocket de : {}", user.getName());
            presenceService.userConnected(sessionId,user.getName());

            // Envoie uniquement à ce user la liste complète
            messagingTemplate.convertAndSendToUser(
                    user.getName(), // username
                    "/queue/presence", // endpoint privé
                    presenceService.getConnectedUsers()
            );

            // Et on broadcast aux autres (facultatif)
            broadcastPresence();
        }
    }

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = accessor.getSessionId();

        presenceService.userDisconnected(sessionId);

        // Broadcast pour tous
        messagingTemplate.convertAndSend("/topic/presence", presenceService.getConnectedUsers());
    }

    private void broadcastPresence() {
        Collection<UserWsOnLineDto> onLineUsers = presenceService.getConnectedUsers();
        messagingTemplate.convertAndSend("/topic/presence", onLineUsers);
    }
}
