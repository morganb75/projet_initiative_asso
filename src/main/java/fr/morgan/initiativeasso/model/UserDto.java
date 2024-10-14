package fr.morgan.initiativeasso.model;

import fr.morgan.initiativeasso.model.enums.PlateForme;
import fr.morgan.initiativeasso.model.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserDto {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String entreprise;
    private Adresse adresse;
    private PlateForme plateForme;
    private UserRole role;
    private String password;
}
