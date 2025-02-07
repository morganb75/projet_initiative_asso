package fr.morgan.initiativeasso.service;

import fr.morgan.initiativeasso.model.Notification;
import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;
import fr.morgan.initiativeasso.repository.NotificationRepository;
import fr.morgan.initiativeasso.repository.UserRepository;
import fr.morgan.initiativeasso.service.interfaces.NotificationService;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Notification> getNotificationsByUserId(Long userId){
        return notificationRepository.findNotificationByReceiverUser_Id(userId);
    }

    @Override
    public Notification notifyUser(Long senderId, Long receiverId) throws UserNotFoundException {

        User senderUser = userRepository.findById(senderId)
                .orElseThrow(() -> UserNotFoundException.builder().message("Sender User not found").build());

        User receiverUser = userRepository.findById(receiverId)
                .orElseThrow(() -> UserNotFoundException.builder().message("Receiver User not found").build());

        return(notificationRepository.save(Notification.builder()
                .dateNotification(LocalDateTime.now())
                .senderUser(senderUser)
                .receiverUser(receiverUser).build()));
    }
}
