package fr.morgan.initiativeasso.model;

import fr.morgan.initiativeasso.model.enums.PlateForme;
import fr.morgan.initiativeasso.model.enums.SecteursReseaux;
import fr.morgan.initiativeasso.model.enums.UserRole;
import fr.morgan.initiativeasso.model.enums.ZonesDeplacement;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Parrain extends User {

    private String parcours;
    private SecteursReseaux domaineActivite;
    private List<ZonesDeplacement> zonesDeDeplacement;
    private String disponibilites;
    @OneToMany(mappedBy = "parrain")
    @JsonManagedReference
    private List<Porteur> listePorteurs;
    @OneToMany(mappedBy = "parrain")
    private List<Reunion> listeReunions;


    @Builder
    public Parrain(Long id, String nom, String prenom, String email, String entreprise, PlateForme plateForme, List<UserRole> roles,
            String password, Boolean isAccountEnabled, Boolean firstLogin, String parcours, SecteursReseaux domaineActivite,
            List<ZonesDeplacement> zonesDeDeplacement, String disponibilites) {
        super(id, nom, prenom, email, entreprise, plateForme, roles, password, isAccountEnabled, firstLogin);
        this.parcours = parcours;
        this.domaineActivite = domaineActivite;
        this.zonesDeDeplacement = zonesDeDeplacement;
        this.disponibilites = disponibilites;
    }
}
