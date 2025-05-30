package fr.morgan.initiativeasso.repository;

import fr.morgan.initiativeasso.model.Reunion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReunionRepository extends JpaRepository<Reunion, Long> {
}
