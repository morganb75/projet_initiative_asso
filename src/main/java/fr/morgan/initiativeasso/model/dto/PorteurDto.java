package fr.morgan.initiativeasso.model.dto;

import fr.morgan.initiativeasso.model.Adresse;
import fr.morgan.initiativeasso.model.enums.SecteursReseaux;
import fr.morgan.initiativeasso.model.enums.TypeAccompagnement;

import java.time.LocalDate;
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
public class PorteurDto {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String entreprise;
    private Adresse adresse;
    private LocalDate dateDebutActivite;
    private SecteursReseaux domaineActivite;
    private String descriptifActivite;
    private List<TypeAccompagnement> besoinsPotentiel;
    private Adresse lieuActivite;
    private String disponibilites;

}
