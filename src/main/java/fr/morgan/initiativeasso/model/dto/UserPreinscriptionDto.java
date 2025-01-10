package fr.morgan.initiativeasso.model.dto;

import fr.morgan.initiativeasso.model.Adresse;
import fr.morgan.initiativeasso.model.enums.PlateForme;
import fr.morgan.initiativeasso.model.enums.UserRole;

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
public class UserPreinscriptionDto {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String entreprise;
    private Adresse adresse;
    private PlateForme plateForme;
    private List<UserRole> roles;
    private String password;
}
