package fr.morgan.initiativeasso.repository;

import fr.morgan.initiativeasso.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<? extends User> findUserByEmail(String email);
    //Recherche des enfants de User par classe
    @Query("SELECT u FROM User u WHERE TYPE(u) = :type")
    <T extends User> List<T> findUsersByType(@Param("type") Class<T> type);
//    @Query("SELECT u FROM User u WHERE TYPE(u) = :type")
//    List<? extends User> findUsersByType(@Param("type") Class<? extends User> type);
}
