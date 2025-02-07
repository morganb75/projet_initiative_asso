package fr.morgan.initiativeasso.repository;

import fr.morgan.initiativeasso.model.Notification;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findNotificationByReceiverUser_Id(long id);
}
