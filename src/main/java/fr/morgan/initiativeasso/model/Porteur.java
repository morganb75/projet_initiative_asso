package fr.morgan.initiativeasso.model;

import fr.morgan.initiativeasso.model.enums.PlateForme;
import fr.morgan.initiativeasso.model.enums.SecteursReseaux;
import fr.morgan.initiativeasso.model.enums.TypeAccompagnement;
import fr.morgan.initiativeasso.model.enums.UserRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
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
public class Porteur extends User {

    private LocalDate dateDebutActivite;
    private SecteursReseaux domaineActivite;
    private String descriptifActivite;
    private List<TypeAccompagnement> besoinsPotentiels;
    //Adresse Pro
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "lieu_activite_id")
    private Adresse lieuActivite;
    private String disponibilites;
    @ManyToOne
    @JoinColumn(name= "parrain_id")
    @JsonManagedReference
//    @JsonBackReference
    private Parrain parrain;


    @Builder
    public Porteur(Long id, String nom, String prenom, String email, String entreprise, PlateForme plateForme, List<UserRole> roles,
            String password, Boolean isAccountEnabled, Boolean firstLogin, LocalDate dateDebutActivite,
            SecteursReseaux domaineActivite,
            String descriptifActivite, List<TypeAccompagnement> besoinsPotentiels, Adresse lieuActivite, String disponibilites) {
        super(id, nom, prenom, email, entreprise, plateForme, roles, password, isAccountEnabled, firstLogin);
        this.dateDebutActivite = dateDebutActivite;
        this.domaineActivite = domaineActivite;
        this.descriptifActivite = descriptifActivite;
        this.besoinsPotentiels = besoinsPotentiels;
        this.lieuActivite = lieuActivite;
        this.disponibilites = disponibilites;
    }
}
