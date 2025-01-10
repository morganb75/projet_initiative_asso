package fr.morgan.initiativeasso.model;

import fr.morgan.initiativeasso.model.enums.PlateForme;
import fr.morgan.initiativeasso.model.enums.UserRole;
import jakarta.persistence.Entity;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class SalarieAsso extends User {
    private String fonction;

    @Builder

    public SalarieAsso(Long id, String nom, String prenom, String email, String entreprise, Adresse adresse, PlateForme plateForme,
            List<UserRole> roles,
            String password, boolean isAccountEnabled, boolean firstLogin, String fonction) {
        super(id, nom, prenom, email, entreprise, adresse, plateForme, roles, password, isAccountEnabled, firstLogin);
        this.fonction = fonction;
    }
}
