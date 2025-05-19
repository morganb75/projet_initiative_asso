package fr.morgan.initiativeasso.model;

import fr.morgan.initiativeasso.model.enums.PlateForme;
import fr.morgan.initiativeasso.model.enums.UserRole;
import jakarta.persistence.Entity;

import java.util.List;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class SalarieAsso extends User {
    private String fonction;

    @Builder
    public SalarieAsso(Long id, String nom, String prenom, String email, String entreprise, PlateForme plateForme,
            List<UserRole> roles,
            String password, Boolean isAccountEnabled, Boolean firstLogin, String fonction) {
        super(id, nom, prenom, email, entreprise, plateForme, roles, password, isAccountEnabled, firstLogin);
        this.fonction = fonction;
    }
}
