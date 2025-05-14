package fr.morgan.initiativeasso.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MessageDto {

    private Long id;
    private Long senderId;
    private Long receiverId;
    private String senderName;
    private String senderFirstName;
    private String text;
    private LocalDateTime time;
    private boolean read;
}
