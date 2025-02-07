package fr.morgan.initiativeasso.service.interfaces;

import fr.morgan.initiativeasso.model.Notification;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;

import java.util.List;

public interface NotificationService {
    List<Notification> getNotificationsByUserId(Long userId);
    Notification notifyUser(Long senderId, Long ReceiverId) throws UserNotFoundException;
}
