package fr.morgan.initiativeasso.service;

import fr.morgan.initiativeasso.model.Adresse;
import fr.morgan.initiativeasso.model.Parrain;
import fr.morgan.initiativeasso.model.Porteur;
import fr.morgan.initiativeasso.model.SalarieAsso;
import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.UserDto;
import fr.morgan.initiativeasso.model.enums.UserRole;
import fr.morgan.initiativeasso.repository.AdresseRepository;
import fr.morgan.initiativeasso.repository.UserRepository;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final AdresseRepository adresseRepository;

    public UserServiceImpl(UserRepository userRepository, AdresseRepository adresseRepository) {
        this.userRepository = userRepository;
        this.adresseRepository = adresseRepository;
    }


    @Override
    public void preInscriptionUser(UserDto user, UserRole role){
        Adresse adresse = user.getAdresse();
        Adresse existingAdresse = adresseRepository.findByNumeroDeVoieAndRueAndComplementAndCodePostalAndVille(adresse.getNumeroDeVoie(),
                    adresse.getRue(), adresse.getComplement(), adresse.getCodePostal(), adresse.getVille());

        if(existingAdresse!=null){
                user.setAdresse(existingAdresse);
        }
        selectionUser(user,role);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    private void selectionUser(UserDto user, UserRole role){
        if (role == UserRole.PORTEUR) {
            Porteur porteur = Porteur.builder()
                    .nom(user.getNom())
                    .prenom(user.getPrenom())
                    .email(user.getEmail())
                    .entreprise(user.getEntreprise())
                    .adresse(user.getAdresse())
                    .plateForme(user.getPlateForme())
                    .password(user.getPassword())
                    .role(user.getRole()).build();
            preInscrireUser(porteur);
        } else if (role == UserRole.PARRAIN){
            Parrain parrain = Parrain.builder()
                    .nom(user.getNom())
                    .prenom(user.getPrenom())
                    .email(user.getEmail())
                    .entreprise(user.getEntreprise())
                    .adresse(user.getAdresse())
                    .plateForme(user.getPlateForme())
                    .password(user.getPassword())
                    .role(user.getRole()).build();
            preInscrireUser(parrain);
        } else {
            SalarieAsso salarieAsso = SalarieAsso.builder()
                    .nom(user.getNom())
                    .prenom(user.getPrenom())
                    .email(user.getEmail())
                    .entreprise(user.getEntreprise())
                    .adresse(user.getAdresse())
                    .plateForme(user.getPlateForme())
                    .password(user.getPassword())
                    .role(user.getRole()).build();
            preInscrireUser(salarieAsso);
        }
    }

    private void preInscrireUser (Porteur porteur) {

        userRepository.save(Porteur.builder()
                .nom(porteur.getNom())
                .prenom(porteur.getPrenom())
                .email(porteur.getEmail())
                .entreprise(porteur.getEntreprise())
                .adresse(porteur.getAdresse())
                .plateForme(porteur.getPlateForme())
                .password(porteur.getPassword())
                .role(porteur.getRole())
                .build());
    }

    private void preInscrireUser(Parrain parrain) {

        userRepository.save(Parrain.builder()
                .nom(parrain.getNom())
                .prenom(parrain.getPrenom())
                .email(parrain.getEmail())
                .entreprise(parrain.getEntreprise())
                .adresse(parrain.getAdresse())
                .plateForme(parrain.getPlateForme())
                .password(parrain.getPassword())
                .role(parrain.getRole()).build());
    }
    private void preInscrireUser(SalarieAsso salarieAsso) {
        userRepository.save(SalarieAsso.builder()
                .nom(salarieAsso.getNom())
                .prenom(salarieAsso.getPrenom())
                .email(salarieAsso.getEmail())
                .entreprise(salarieAsso.getEntreprise())
                .adresse(salarieAsso.getAdresse())
                .plateForme(salarieAsso.getPlateForme())
                .password(salarieAsso.getPassword())
                .role(salarieAsso.getRole()).build());
    }
}
