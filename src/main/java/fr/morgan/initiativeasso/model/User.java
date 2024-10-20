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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "`user`")
@Inheritance(strategy = InheritanceType.JOINED)
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PlateForme plateForme;
//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
    //   private UserRole role;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "roles")
    private List<UserRole> roles;
    @Column(nullable = false)
    private String password;

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //      return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .collect(Collectors.toList());
    }

    //   @Override
    //   public boolean isAccountNonExpired() {
    //      return true;
    //   }
    //
    //   @Override
    //   public boolean isAccountNonLocked() {
    //      return true;
    //   }
    //
    //   @Override
    //   public boolean isCredentialsNonExpired() {
    //      return true;
    //   }
    //
    //   @Override
    //   public boolean isEnabled() {
    //      return true;
    //   }
}

