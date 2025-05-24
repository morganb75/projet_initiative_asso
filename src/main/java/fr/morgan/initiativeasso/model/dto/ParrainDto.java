package fr.morgan.initiativeasso.model.dto;

import fr.morgan.initiativeasso.model.enums.SecteursReseaux;
import fr.morgan.initiativeasso.model.enums.ZonesDeplacement;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ParrainDto {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String entreprise;
    private String parcours;
    private SecteursReseaux domaineActivite;
    private List<ZonesDeplacement> zonesDeDeplacement;
    private String disponibilites;

}
