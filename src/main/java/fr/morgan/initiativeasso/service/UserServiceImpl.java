package fr.morgan.initiativeasso.service;

import fr.morgan.initiativeasso.model.Adresse;
import fr.morgan.initiativeasso.model.Parrain;
import fr.morgan.initiativeasso.model.Porteur;
import fr.morgan.initiativeasso.model.SalarieAsso;
import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.dto.UserDto;
import fr.morgan.initiativeasso.model.enums.UserRole;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Pas d'utilisateur correspondant à ce username")));
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
                    .roles(List.of(UserRole.PORTEUR)).build();
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
                    .roles(List.of(UserRole.PARRAIN)).build();
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
                    .roles(List.of(UserRole.ASSO, UserRole.ADMIN)).build();
            userRepository.save(salarieAsso);
        }
//
//        switch (role) {
//            case PORTEUR -> {
//                Porteur porteur = Porteur.builder()
//                        .nom(user.getNom())
//                        .prenom(user.getPrenom())
//                        .email(user.getEmail())
//                        .entreprise(user.getEntreprise())
//                        .adresse(user.getAdresse())
//                        .plateForme(user.getPlateForme())
//                        .password(passwordEncoder.encode(user.getPassword()))
//                        .roles(List.of(UserRole.PORTEUR)).build();
//                userRepository.save(porteur);
//            }
//            case PARRAIN -> {
//                Parrain parrain = Parrain.builder()
//                        .nom(user.getNom())
//                        .prenom(user.getPrenom())
//                        .email(user.getEmail())
//                        .entreprise(user.getEntreprise())
//                        .adresse(user.getAdresse())
//                        .plateForme(user.getPlateForme())
//                        .password(passwordEncoder.encode(user.getPassword()))
//                        .roles(List.of(UserRole.PARRAIN)).build();
//                userRepository.save(parrain);
//            }
//            case ASSO -> {
//                SalarieAsso salarieAsso = SalarieAsso.builder()
//                        .nom(user.getNom())
//                        .prenom(user.getPrenom())
//                        .email(user.getEmail())
//                        .entreprise(user.getEntreprise())
//                        .adresse(user.getAdresse())
//                        .plateForme(user.getPlateForme())
//                        .password(passwordEncoder.encode(user.getPassword()))
//                        .roles(List.of(UserRole.ASSO, UserRole.ADMIN)).build();
//                userRepository.save(salarieAsso);
//            }
//        }
    }
}
