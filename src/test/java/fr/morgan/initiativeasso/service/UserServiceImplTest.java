package fr.morgan.initiativeasso.service;

import fr.morgan.initiativeasso.model.Adresse;
import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.dto.UserDto;
import fr.morgan.initiativeasso.model.dto.UserPreinscriptionDto;
import fr.morgan.initiativeasso.repository.AdresseRepository;
import fr.morgan.initiativeasso.repository.UserRepository;
import fr.morgan.initiativeasso.service.mapper.UserMapper;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private AdresseRepository adresseRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void preInscriptionUser_shouldUseExistingAdresseIfFound() {
        // Arrange
        Adresse adresse = new Adresse();
        adresse.setNumeroDeVoie("12");
        adresse.setRue("Rue des Fleurs");
        adresse.setComplement("");
        adresse.setCodePostal(75000);
        adresse.setVille("Paris");

        UserPreinscriptionDto dto = new UserPreinscriptionDto();
        dto.setAdresse(adresse);

        Adresse existingAdresse = new Adresse();
        existingAdresse.setId(1L);

        when(adresseRepository.findByNumeroDeVoieAndRueAndComplementAndCodePostalAndVille(
                adresse.getNumeroDeVoie(),
                adresse.getRue(),
                adresse.getComplement(),
                adresse.getCodePostal(),
                adresse.getVille()
        )).thenReturn(Optional.of(existingAdresse));

        // Act
        userService.preInscriptionUser(dto);

        // Assert
        assertEquals(existingAdresse, dto.getAdresse());
        verify(adresseRepository).findByNumeroDeVoieAndRueAndComplementAndCodePostalAndVille(
                adresse.getNumeroDeVoie(),
                adresse.getRue(),
                adresse.getComplement(),
                adresse.getCodePostal(),
                adresse.getVille()
        );

        verify(userRepository, times(1)).save(any(User.class));
    }
}
