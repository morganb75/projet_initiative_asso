package fr.morgan.initiativeasso.model;

import fr.morgan.initiativeasso.model.enums.PlateForme;
import fr.morgan.initiativeasso.model.enums.SecteursReseaux;
import fr.morgan.initiativeasso.model.enums.UserRole;
import fr.morgan.initiativeasso.model.enums.ZonesDeplacement;
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
public class Parrain extends User {

    private String parcours;
    private SecteursReseaux domaineActivite;
    private List<ZonesDeplacement> zonesDeDeplacement;
    private String disponibilites;

    @Builder
    public Parrain(Long id, String nom, String prenom, String email, String entreprise, Adresse adresse, PlateForme plateForme, List<UserRole> roles,
            String password, String parcours, SecteursReseaux domaineActivite, List<ZonesDeplacement> zonesDeDeplacement, String disponibilites) {
        super(id, nom, prenom, email, entreprise, adresse, plateForme, roles, password);
        this.parcours = parcours;
        this.domaineActivite = domaineActivite;
        this.zonesDeDeplacement = zonesDeDeplacement;
        this.disponibilites = disponibilites;
    }
}
