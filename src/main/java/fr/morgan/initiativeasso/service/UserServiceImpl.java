package fr.morgan.initiativeasso.service;

import fr.morgan.initiativeasso.config.jwt.JwtService;
import fr.morgan.initiativeasso.exceptions.ParrainAlreadyExist;
import fr.morgan.initiativeasso.model.Adresse;
import fr.morgan.initiativeasso.model.Parrain;
import fr.morgan.initiativeasso.model.Porteur;
import fr.morgan.initiativeasso.model.SalarieAsso;
import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.dto.UserDto;
import fr.morgan.initiativeasso.model.dto.UserPreinscriptionDto;
import fr.morgan.initiativeasso.model.enums.UserRole;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;
import fr.morgan.initiativeasso.repository.AdresseRepository;
import fr.morgan.initiativeasso.repository.UserRepository;
import fr.morgan.initiativeasso.service.interfaces.UserService;
import fr.morgan.initiativeasso.service.mapper.UserMapper;

import java.util.List;
import java.util.Map;
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
    private final UserMapper userMapper;
    private static final Long ADMIN_ID = 1L;

    public UserServiceImpl(UserRepository userRepository, AdresseRepository adresseRepository, BCryptPasswordEncoder passwordEncoder,
            UserMapper userMapper) {
        this.userRepository = userRepository;
        this.adresseRepository = adresseRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public void preInscriptionUser(UserPreinscriptionDto user) {
        Adresse adresse = user.getAdresse();
        Adresse existingAdresse = adresseRepository.findByNumeroDeVoieAndRueAndComplementAndCodePostalAndVille(adresse.getNumeroDeVoie(),
                adresse.getRue(), adresse.getComplement(), adresse.getCodePostal(), adresse.getVille()).orElse(adresse);
        user.setAdresse(existingAdresse);
        saveTypedUser(user);
    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .map(userMapper::userToDto);
    }

    @Override
    public UserDto findPorteurDtoByEmail(String email) throws UserNotFoundException {
        Porteur porteur = (Porteur) userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("pas de user avec cet email"));
        return userMapper.porteurToDto(porteur);
    }

    @Override
    public Optional<User> findById(Long id) throws UserNotFoundException {
        return (userRepository.findById(id));
    }

    @Override
    public void validationInscription(Long id) {
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getUsers() {
        return userMapper.adminLstToDto(userRepository.findAll());
    }

    @Override
    public User updatedUser(Long id, User updatedUser) throws UserNotFoundException {
        User userToUpdate = updatingUser(id, updatedUser);
        return userRepository.save(userToUpdate);
    }

    @Override
    public List<UserDto> findAllPorteurs() {
        return userMapper.porteurLstToDto(userRepository.findUsersByType(Porteur.class));
    }

    @Override
    public List<UserDto> findAllParrains() {
        return userMapper.parrainLstToDto(userRepository.findUsersByType(Parrain.class));
    }

    @Override
    public List<UserDto> feedPorteur(Long parrainId) {
        User admin = userRepository.findById(ADMIN_ID).orElseThrow(() ->new UsernameNotFoundException("probleme BDD Admin"));
        User parrain = userRepository.findById(parrainId).orElseThrow(() ->new UsernameNotFoundException("User Not Found"));
        UserDto adminDto = userMapper.userToDto(admin);
        UserDto parrainDto = userMapper.userToDto(parrain);
        return List.of(adminDto,parrainDto);
    }

    @Override
    public List<UserDto> feedParrain(Long parrainId) {
        User user= userRepository.findById(parrainId).orElseThrow(() ->new UsernameNotFoundException("User Not Found"));
        User admin = userRepository.findById(ADMIN_ID).orElseThrow(() ->new UsernameNotFoundException("probleme BDD Admin"));
        UserDto adminDto = userMapper.userToDto(admin);
        List<UserDto> lstPorteurs= userMapper.porteurLstToDto(((Parrain) user).getListePorteurs());
        lstPorteurs.add(adminDto);
        return lstPorteurs;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Pas d'utilisateur correspondant au username" + username)));
    }

    @Override
    public User setParrain(Long id, Long parrainId) throws UserNotFoundException, ParrainAlreadyExist {
        Porteur currentUser = (Porteur) userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Pas d'utilisateur correspondant au parrain"));
        if (currentUser.getParrain() == null) {
            User parrain = userRepository.findById(parrainId)
                    .orElseThrow(() -> new UserNotFoundException("Pas d'utilisateur correspondant au parrain"));
            currentUser.setParrain((Parrain) parrain);
        } else {
            throw new ParrainAlreadyExist("Vous avez déjà un parrain d'affecté!");
        }
        return userRepository.save(currentUser);
    }

    private void saveTypedUser(UserPreinscriptionDto user) {
        List<UserRole> roles = user.getRoles();
        if (roles.contains(UserRole.PORTEUR)) {
            Porteur porteur = Porteur.builder()
                    .nom(user.getNom())
                    .prenom(user.getPrenom())
                    .email(user.getEmail())
                    .entreprise(user.getEntreprise())
                    .plateForme(user.getPlateForme())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .roles(user.getRoles())
                    .isAccountEnabled(true)
                    .firstLogin(true).build();
            userRepository.save(porteur);
        } else if (roles.contains(UserRole.PARRAIN)) {
            Parrain parrain = Parrain.builder()
                    .nom(user.getNom())
                    .prenom(user.getPrenom())
                    .email(user.getEmail())
                    .entreprise(user.getEntreprise())
                    .plateForme(user.getPlateForme())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .roles(user.getRoles())
                    .isAccountEnabled(true)
                    .firstLogin(true).build();
            userRepository.save(parrain);
        } else {
            SalarieAsso salarieAsso = SalarieAsso.builder()
                    .nom(user.getNom())
                    .prenom(user.getPrenom())
                    .email(user.getEmail())
                    .entreprise(user.getEntreprise())
                    .plateForme(user.getPlateForme())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .roles(user.getRoles())
                    .isAccountEnabled(true)
                    .firstLogin(true).build();
            userRepository.save(salarieAsso);
        }
    }

    private <T extends User> T updatingUser(Long id, T updatedFields) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found!"));
        if (user instanceof Porteur porteurToUpdate) {
            Porteur porteurUpdated = (Porteur) updateProcessing(user, updatedFields);
            return (T) porteurUpdated;
        } else if (user instanceof Parrain parrainToUpdate) {
            Parrain parrainUpdated = (Parrain) updateProcessing(user, updatedFields);
            return (T) parrainUpdated;
        } else {
            throw new IllegalArgumentException("L'utilisateur trouvé n'est ni un Porteur ni un Parrain");
        }
    }

    private <T extends User> T updateProcessing(T userToUpdate, T updatedFields) {

        if (updatedFields.getNom() != null) {
            userToUpdate.setNom(updatedFields.getNom());
        }
        if (updatedFields.getPrenom() != null) {
            userToUpdate.setPrenom(updatedFields.getPrenom());
        }
        if ((updatedFields.getEmail() != null) && !updatedFields.getEmail().equals(userToUpdate.getEmail())) {
            userToUpdate.setEmail(updatedFields.getEmail());
        }
        if (updatedFields.getEntreprise() != null) {
            userToUpdate.setEntreprise(updatedFields.getEntreprise());
        }
        if (updatedFields.getPassword() != null) {
            userToUpdate.setPassword(passwordEncoder.encode((updatedFields.getPassword())));
        }
        userToUpdate.setFirstLogin(false);

        if (userToUpdate instanceof Porteur porteurToUpdate
                && updatedFields instanceof Porteur updatedPorteur) {
            if (updatedPorteur.getDateDebutActivite() != null) {
                porteurToUpdate.setDateDebutActivite(updatedPorteur.getDateDebutActivite());
            }
            if (updatedPorteur.getDomaineActivite() != null) {
                porteurToUpdate.setDomaineActivite(updatedPorteur.getDomaineActivite());
            }
            if (updatedPorteur.getBesoinsPotentiels() != null) {
                porteurToUpdate.setBesoinsPotentiels(updatedPorteur.getBesoinsPotentiels());
            }
            if (updatedPorteur.getDisponibilites() != null) {
                porteurToUpdate.setDisponibilites(updatedPorteur.getDisponibilites());
            }
            if (updatedPorteur.getDescriptifActivite() != null) {
                porteurToUpdate.setDescriptifActivite(updatedPorteur.getDescriptifActivite());
            }
            if (updatedPorteur.getLieuActivite() != null) {
                Adresse updatedAdresse = updatedPorteur.getLieuActivite();
                Adresse adresse = adresseRepository.findByNumeroDeVoieAndRueAndComplementAndCodePostalAndVille(updatedAdresse.getNumeroDeVoie(),
                                updatedAdresse.getRue(), updatedAdresse.getComplement(), updatedAdresse.getCodePostal(), updatedAdresse.getVille())
                        .orElse(updatedAdresse);
                porteurToUpdate.setLieuActivite(adresse);
            }
        }
        if (userToUpdate instanceof Parrain parrainToUpdate && updatedFields instanceof Parrain updatedParrain) {
            if (updatedParrain.getParcours() != null) {
                parrainToUpdate.setParcours(updatedParrain.getParcours());
            }
            if (updatedParrain.getDomaineActivite() != null) {
                parrainToUpdate.setDomaineActivite(updatedParrain.getDomaineActivite());
            }
            if (updatedParrain.getZonesDeDeplacement() != null) {
                parrainToUpdate.setZonesDeDeplacement(updatedParrain.getZonesDeDeplacement());
            }
            if (updatedParrain.getDisponibilites() != null) {
                parrainToUpdate.setDisponibilites(updatedParrain.getDisponibilites());
            }
        }
        return userToUpdate;
    }
}

