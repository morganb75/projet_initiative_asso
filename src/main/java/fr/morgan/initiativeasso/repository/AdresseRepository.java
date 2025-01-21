package fr.morgan.initiativeasso.repository;

import fr.morgan.initiativeasso.model.Adresse;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse,Long> {
    Optional<Adresse> findByNumeroDeVoieAndRueAndComplementAndCodePostalAndVille(String numeroDeVoie, String rue, String complement,Integer codePostal, String ville);

}
