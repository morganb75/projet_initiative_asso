package fr.morgan.initiativeasso.service;

import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.dto.UserDto;
import fr.morgan.initiativeasso.model.enums.DomainesActivite;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    void preInscriptionUser(UserDto user);

    Optional<User> findByEmail(String email);

    User findById(Long id) throws UserNotFoundException;

    void validationInscription(Long id);

    void deleteUser(Long id);

    List<User> getUsers();


    // Renvoie l'utilisateur de la BDD quand il existe au DAO Provider (userDetailService)
    // Implementé dans UserServiceImpl
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    List<User> findByDomaineActivite(DomainesActivite domainesActivite);
}
