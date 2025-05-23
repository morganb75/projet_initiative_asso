package fr.morgan.initiativeasso.model;

import fr.morgan.initiativeasso.model.enums.PlateForme;
import fr.morgan.initiativeasso.model.enums.UserRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "`user`")
@Inheritance(strategy = InheritanceType.JOINED)

// Indique que les sous-types sont inclus dans les données JSON
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME, // Utiliser un champ "type" pour identifier le sous-type
        include = JsonTypeInfo.As.PROPERTY, // Placer le type dans une propriété JSON
        property = "type" // Le nom du champ pour le type
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Porteur.class, name = "PORTEUR"),
        @JsonSubTypes.Type(value = Parrain.class, name = "PARRAIN")
})
public abstract class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String entreprise;
    //TODO gérer orphanRemoval le moment venu (besoin d'une bidirectionnelle ou gestion à la main).
    //Todo adresse privée, non nécéssaire mais existe toutefois
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PlateForme plateForme;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "roles")
    private List<UserRole> roles;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Boolean isAccountEnabled;
    @Column(nullable = false)
    private Boolean firstLogin;

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isEnabled() {
        return this.isAccountEnabled;
    }

}

