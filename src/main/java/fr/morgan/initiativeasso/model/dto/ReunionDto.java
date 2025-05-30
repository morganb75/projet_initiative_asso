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
public class ReunionDto {
    private Long id;
    private LocalDateTime date;
    private String motif;
    private String compteRendu;
    private ParrainDto parrain;
    private PorteurDto porteur;
}
