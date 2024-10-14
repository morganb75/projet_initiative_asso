package fr.morgan.initiativeasso.model;

import fr.morgan.initiativeasso.model.enums.PlateForme;
import fr.morgan.initiativeasso.model.enums.UserRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class User{

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(nullable = false)
   private String nom;
   @Column(nullable = false)
   private String prenom;
   @Column(nullable = false,unique = true)
   private String email;
   @Column(nullable = false, unique = true)
   private String entreprise;
   @ManyToOne (cascade = CascadeType.ALL)
   @JoinColumn(name = "adresse_id")
   private Adresse adresse;
   @Column(nullable = false)
   private PlateForme plateForme;
   @Column(nullable = false)
   private UserRole role;
   @Column(nullable = false)
   private String password;
}
