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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;
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
    //TODO finir implementation like
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Like> likes = new HashSet<>();
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof User user))
            return false;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getNom(), user.getNom()) && Objects.equals(
                getPrenom(), user.getPrenom()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getEntreprise(),
                user.getEntreprise()) && Objects.equals(getAdresse(), user.getAdresse()) && getPlateForme() == user.getPlateForme()
                && Objects.equals(getRoles(), user.getRoles()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNom(), getPrenom(), getEmail(), getEntreprise(), getAdresse(), getPlateForme(), getRoles(), getPassword());
    }
}

