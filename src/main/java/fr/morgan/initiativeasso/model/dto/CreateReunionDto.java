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
public class CreateReunionDto {
    private LocalDateTime date;
    private String motif;
    private Long parrainId;
    private Long porteurId;
}
