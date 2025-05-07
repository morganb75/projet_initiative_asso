package fr.morgan.initiativeasso.model.dto;

import fr.morgan.initiativeasso.model.User;

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

    private String text;
    private Long senderId;
    private Long receiverId;
    private LocalDateTime time;
}
