package fr.morgan.initiativeasso.repository;

import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.enums.DomainesActivite;
import fr.morgan.initiativeasso.model.enums.ZonesDeplacement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<? extends User> findUserByEmail(String email);

    List<User> findUsersByDomaineActivite(DomainesActivite domainesActivite);

    List<User> findUsersByZoneGeographique(ZonesDeplacement zonesDeplacement);
}
