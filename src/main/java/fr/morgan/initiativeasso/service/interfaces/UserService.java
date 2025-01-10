package fr.morgan.initiativeasso.service.interfaces;

import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.dto.UserPreinscriptionDto;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    void preInscriptionUser(UserPreinscriptionDto user);

    Optional<User> findByEmail(String email) throws UserNotFoundException;

    Optional<User> findById(Long id) throws UserNotFoundException;

    void validationInscription(Long id);

    void deleteUser(Long id);

    List<User> getUsers();

    // Renvoie l'utilisateur de la BDD quand il existe au DAO Provider (userDetailService)
    // Implementé dans UserServiceImpl
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
