package fr.morgan.initiativeasso.model.dto;

import fr.morgan.initiativeasso.model.Adresse;
import fr.morgan.initiativeasso.model.Parrain;
import fr.morgan.initiativeasso.model.Porteur;
import fr.morgan.initiativeasso.model.enums.SecteursReseaux;
import fr.morgan.initiativeasso.model.enums.TypeAccompagnement;
import fr.morgan.initiativeasso.model.enums.UserRole;
import fr.morgan.initiativeasso.model.enums.ZonesDeplacement;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String entreprise;
    private List<UserRole> roles;
    //communs Parrain Porteur
    private SecteursReseaux domaineActivite;
    private String disponibilites;
    //User est un Porteur
    private LocalDate dateDebutActivite;
    private String descriptifActivite;
    private List<TypeAccompagnement> besoinsPotentiels;
    private Adresse lieuActivite;
    private Long parrainId;
    private String parrainNom;
    private String parrainPrenom;
    private String parrainEmail;
    //User est un Parrain
    private String parcours;
    private List<ZonesDeplacement> zonesDeDeplacement;
}
