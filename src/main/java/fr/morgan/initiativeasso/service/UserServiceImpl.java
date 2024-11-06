package fr.morgan.initiativeasso.service;

import fr.morgan.initiativeasso.model.Adresse;
import fr.morgan.initiativeasso.model.Parrain;
import fr.morgan.initiativeasso.model.Porteur;
import fr.morgan.initiativeasso.model.SalarieAsso;
import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.dto.UserDto;
import fr.morgan.initiativeasso.model.enums.DomainesActivite;
import fr.morgan.initiativeasso.model.enums.UserRole;
import fr.morgan.initiativeasso.model.enums.ZonesDeplacement;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;
import fr.morgan.initiativeasso.repository.AdresseRepository;
import fr.morgan.initiativeasso.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AdresseRepository adresseRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, AdresseRepository adresseRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.adresseRepository = adresseRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void preInscriptionUser(UserDto user) {
        Adresse adresse = user.getAdresse();
        Adresse existingAdresse = adresseRepository.findByNumeroDeVoieAndRueAndComplementAndCodePostalAndVille(adresse.getNumeroDeVoie(),
                adresse.getRue(), adresse.getComplement(), adresse.getCodePostal(), adresse.getVille());

        if (existingAdresse != null) {
            user.setAdresse(existingAdresse);
        }
        saveTypedUser(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return (Optional<User>) userRepository.findUserByEmail(email);
    }

    @Override
    public User findById(Long id) throws UserNotFoundException {
        return (userRepository.findById(id))
                .orElseThrow(() -> new UserNotFoundException("Aucun utilisateur portant l'Id " + id));
    }

    @Override
    public void validationInscription(Long id) {

    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Pas d'utilisateur correspondant au username" + username)));
    }

    @Override
    public List<User> findByDomaineActivite(DomainesActivite domainesActivite) {
        return userRepository.findUsersByDomaineActivite(domainesActivite);
    }

    @Override
    public List<User> findByZoneGeographique(ZonesDeplacement zonesDeplacement) {
        return userRepository.findUsersByZoneGeographique(zonesDeplacement);
    }

    private void saveTypedUser(UserDto user) {
        List<UserRole> roles = user.getRoles();
        if (roles.contains(UserRole.PORTEUR)) {
            Porteur porteur = Porteur.builder()
                    .nom(user.getNom())
                    .prenom(user.getPrenom())
                    .email(user.getEmail())
                    .entreprise(user.getEntreprise())
                    .adresse(user.getAdresse())
                    .plateForme(user.getPlateForme())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .roles(user.getRoles()).build();
            userRepository.save(porteur);
        } else if (roles.contains(UserRole.PARRAIN)) {
            Parrain parrain = Parrain.builder()
                    .nom(user.getNom())
                    .prenom(user.getPrenom())
                    .email(user.getEmail())
                    .entreprise(user.getEntreprise())
                    .adresse(user.getAdresse())
                    .plateForme(user.getPlateForme())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .roles(user.getRoles()).build();
            userRepository.save(parrain);
        } else {
            SalarieAsso salarieAsso = SalarieAsso.builder()
                    .nom(user.getNom())
                    .prenom(user.getPrenom())
                    .email(user.getEmail())
                    .entreprise(user.getEntreprise())
                    .adresse(user.getAdresse())
                    .plateForme(user.getPlateForme())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .roles(user.getRoles()).build();
            userRepository.save(salarieAsso);
        }
    }
}
