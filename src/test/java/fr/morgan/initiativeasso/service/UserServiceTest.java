package fr.morgan.initiativeasso.service;

import fr.morgan.initiativeasso.model.Parrain;
import fr.morgan.initiativeasso.model.Porteur;
import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.enums.DomainesActivite;
import fr.morgan.initiativeasso.model.enums.ZonesDeplacement;
import fr.morgan.initiativeasso.repository.UserRepository;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void findUserByDomaineActiviteTest() {

        Parrain parrain1 = Parrain.builder()
                .domaineActivite(DomainesActivite.INDUSTRIE)
                .build();
        Parrain parrain2 = Parrain.builder()
                .domaineActivite(DomainesActivite.ENSEIGNEMENT)
                .build();
        Porteur porteur1 = Porteur.builder()
                .domaineActivite(DomainesActivite.INDUSTRIE)
                .build();
        Porteur porteur2 = Porteur.builder()
                .domaineActivite(DomainesActivite.ENSEIGNEMENT)
                .build();

        List<User> liste1 = List.of(porteur1, parrain1);
        List<User> liste2 = List.of(porteur2, parrain2);

        //GIVEN
        when(userRepository.findUsersByDomaineActivite(DomainesActivite.INDUSTRIE)).thenReturn(liste1);
        when(userRepository.findUsersByDomaineActivite(DomainesActivite.ENSEIGNEMENT)).thenReturn(liste2);

        //WHEN
        List<User> result1 = userService.findByDomaineActivite(DomainesActivite.INDUSTRIE);
        List<User> result2 = userService.findByDomaineActivite(DomainesActivite.ENSEIGNEMENT);

        //THEN
        Assertions.assertEquals(result1, liste1);
        Assertions.assertEquals(result2, liste2);
    }

    @Test
    public void findUsersByZoneGeographiqueTest() {
        Parrain parrain1 = Parrain.builder()
                .zonesDeDeplacement(List.of(ZonesDeplacement.NIORT, ZonesDeplacement.BRESSUIRE))
                .build();
        Parrain parrain2 = Parrain.builder()
                .zonesDeDeplacement(List.of(ZonesDeplacement.NIORT, ZonesDeplacement.MELLE))
                .build();
        Porteur porteur1 = Porteur.builder()
                .zoneActivite(ZonesDeplacement.NIORT)
                .build();
        Porteur porteur2 = Porteur.builder()
                .zoneActivite(ZonesDeplacement.MELLE)
                .build();

        List<User> liste1 = List.of(porteur1, parrain1, parrain2);
        List<User> liste2 = List.of(porteur2, parrain2);

        //GIVEN
        when(userRepository.findUsersByZoneGeographique(ZonesDeplacement.NIORT)).thenReturn(liste1);
        when(userRepository.findUsersByZoneGeographique(ZonesDeplacement.MELLE)).thenReturn(liste2);
        when(userRepository.findUsersByZoneGeographique(ZonesDeplacement.THOUARS)).thenReturn(Collections.EMPTY_LIST);

        //WHEN
        List<User> result1 = userService.findByZoneGeographique(ZonesDeplacement.NIORT);
        List<User> result2 = userService.findByZoneGeographique(ZonesDeplacement.MELLE);
        List<User> result3 = userService.findByZoneGeographique(ZonesDeplacement.THOUARS);

        //THEN
        Assertions.assertEquals(result1, liste1);
        Assertions.assertEquals(result2, liste2);
        Assertions.assertEquals(result3, Collections.EMPTY_LIST);
    }
}
