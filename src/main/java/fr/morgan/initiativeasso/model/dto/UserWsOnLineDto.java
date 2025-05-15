package fr.morgan.initiativeasso.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserWsOnLineDto {

    private Long id;
    private String userName;
 }
