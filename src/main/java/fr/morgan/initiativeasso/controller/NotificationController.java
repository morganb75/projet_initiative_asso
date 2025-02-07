package fr.morgan.initiativeasso.controller;

import fr.morgan.initiativeasso.model.Notification;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;
import fr.morgan.initiativeasso.service.interfaces.NotificationService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notify")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getNotificationsByUserId(userId));
    }

    @PostMapping("/{senderId}/to/{receiverId}")
    public ResponseEntity<Notification> notify(@PathVariable Long senderId, @PathVariable Long receiverId) throws UserNotFoundException {
        return ResponseEntity.ok(notificationService.notifyUser(senderId, receiverId));
    }
}
