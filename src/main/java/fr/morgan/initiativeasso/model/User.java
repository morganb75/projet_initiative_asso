package fr.morgan.initiativeasso.model;

import fr.morgan.initiativeasso.model.enums.PlateForme;
import fr.morgan.initiativeasso.model.enums.UserRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name ="`user`")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(nullable = false)
   private String nom;
   @Column(nullable = false)
   private String prenom;
   @Column(nullable = false,unique = true)
   private String email;
   @Column(nullable = false)
   private String entreprise;
   @ManyToOne (cascade = CascadeType.ALL)
   @JoinColumn(name = "adresse_id")
   private Adresse adresse;
   @Column(nullable = false)
   @Enumerated(EnumType.STRING)
   private PlateForme plateForme;
   @Column(nullable = false)
   @Enumerated(EnumType.STRING)
   private UserRole role;
   @Column(nullable = false)
   private String password;

   @Override
   public String getUsername() {
      return this.getEmail();
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return Collections.singletonList(new SimpleGrantedAuthority(this.role.name()));
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

