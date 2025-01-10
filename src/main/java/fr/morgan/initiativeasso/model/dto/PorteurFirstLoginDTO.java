package fr.morgan.initiativeasso.model.dto;

import fr.morgan.initiativeasso.model.Adresse;
import fr.morgan.initiativeasso.model.TypeAccompagnement;
import fr.morgan.initiativeasso.model.enums.PlateForme;
import fr.morgan.initiativeasso.model.enums.SecteursReseaux;
import fr.morgan.initiativeasso.model.enums.UserRole;

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
public class PorteurFirstLoginDTO {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String entreprise;
    private Adresse adresse;
    private PlateForme plateForme;
    private String password;
    private LocalDate dateDebutActivite;
    private SecteursReseaux domaineActivite;
    private String descriptifActivite;
    private List<TypeAccompagnement> besoinsPotentiel;
    private Adresse lieuActivite;
    private String disponibilites;

}
