package fr.morgan.initiativeasso.service.interfaces;

import fr.morgan.initiativeasso.exceptions.ParrainAlreadyExist;
import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.dto.UserDto;
import fr.morgan.initiativeasso.model.dto.UserPreinscriptionDto;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    void preInscriptionUser(UserPreinscriptionDto user);
    Optional<UserDto> findByEmail(String email) throws UserNotFoundException;
    UserDto findPorteurDtoByEmail(String email) throws UserNotFoundException;
    Optional<User> findById(Long id) throws UserNotFoundException;
    void validationInscription(Long id);
    void deleteUser(Long id);
    List<UserDto> getUsers();
    public User updatedUser(Long id, User updatedUser) throws UserNotFoundException;
    public User setParrain(Long id, Long parrainId) throws UserNotFoundException, ParrainAlreadyExist;
    public List<UserDto> findAllPorteurs();
    public List<UserDto> findAllParrains();
    public List<UserDto> feedPorteur(Long parrainId);
    public List<UserDto> feedParrain(Long parrainId);

    // Renvoie l'utilisateur de la BDD quand il existe au DAO Provider (userDetailService)
    // Implementé dans UserServiceImpl
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
