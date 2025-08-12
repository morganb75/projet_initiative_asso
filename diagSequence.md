````mermaid
sequenceDiagram
    participant Porteur
    participant Backend
    participant DB
    participant Parrain
    participant NotificationService

    Porteur->>Backend: POST /messages (text, receiverId)
    Backend->>DB: INSERT INTO message (sender_id, receiver_id, text)
    DB-->>Backend: Ack (id, timestamp)
    Backend->>NotificationService: sendNotification(receiverId, "Nouveau message")
    NotificationService->>DB: INSERT INTO notification (receiver_id, sender_id, date)
    DB-->>NotificationService: Ack
    NotificationService-->>Backend: Ack
    Backend-->>Porteur: Message envoyé avec succès

    Parrain->>Backend: GET /messages
    Backend->>DB: SELECT * FROM message WHERE receiver_id = Parrain.id
    DB-->>Backend: Liste des messages
    Backend-->>Parrain: Liste des messages

````