package fr.morgan.initiativeasso.service;

import fr.morgan.initiativeasso.model.Adresse;
import fr.morgan.initiativeasso.model.Porteur;
import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.dto.UserDto;
import fr.morgan.initiativeasso.model.dto.UserPreinscriptionDto;
import fr.morgan.initiativeasso.model.enums.PlateForme;
import fr.morgan.initiativeasso.model.enums.UserRole;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;
import fr.morgan.initiativeasso.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // reset H2 après chaque test
class UserServiceImplIntegrationTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    private UserPreinscriptionDto porteurDto;

    @BeforeEach
    void setUp() {

        Adresse adresse = new Adresse();
        adresse.setNumeroDeVoie("11");
        adresse.setRue("rue test");
        adresse.setCodePostal(79000);
        adresse.setVille("NIORT");

        porteurDto = new UserPreinscriptionDto();
        porteurDto.setNom("Doe");
        porteurDto.setPrenom("John");
        porteurDto.setEmail("john.doe@test.com");
        porteurDto.setAdresse(adresse);
        porteurDto.setPassword("password123");
        porteurDto.setEntreprise("StartupTest");
        porteurDto.setPlateForme(PlateForme.DEUXSEVRES);
        porteurDto.setRoles(List.of(UserRole.PORTEUR));
    }

    @Test
    void testPreInscriptionUser_shouldPersistPorteurInDatabase() {
        // WHEN
        userService.preInscriptionUser(porteurDto);

        // THEN
        Optional<User> userOpt = (Optional<User>) userRepository.findUserByEmail("john.doe@test.com");
        assertThat(userOpt).isPresent();
        assertThat(userOpt.get()).isInstanceOf(Porteur.class);
        assertThat(userOpt.get().getNom()).isEqualTo("Doe");
    }

    @Test
    void testFindByEmail_shouldReturnUserDto() {
        // GIVEN
        userService.preInscriptionUser(porteurDto);

        // WHEN
        Optional<UserDto> dto = userService.findByEmail("john.doe@test.com");

        // THEN
        assertThat(dto).isPresent();
        assertThat(dto.get().getNom()).isEqualTo("Doe");
        assertThat(dto.get().getPrenom()).isEqualTo("John");
    }

    @Test
    void testUpdatedUser_shouldUpdateFields() throws UserNotFoundException {
        // GIVEN
        userService.preInscriptionUser(porteurDto);
        User existing = userRepository.findUserByEmail("john.doe@test.com").orElseThrow();

        Porteur updated = Porteur.builder()
                .nom("Smith")
                .prenom("Johnny")
                .email("johnny.smith@test.com")
                .password("newpassword")
                .entreprise("UpdatedEntreprise")
                .build();

        // WHEN
        User updatedUser = userService.updatedUser(existing.getId(), updated);

        // THEN
        assertThat(updatedUser.getNom()).isEqualTo("Smith");
        assertThat(updatedUser.getEntreprise()).isEqualTo("UpdatedEntreprise");
        assertThat(updatedUser.getFirstLogin()).isFalse();
    }
}
