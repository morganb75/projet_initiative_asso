package fr.morgan.initiativeasso.config.jwt;

import fr.morgan.initiativeasso.model.Porteur;
import fr.morgan.initiativeasso.model.SalarieAsso;
import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.enums.UserRole;
import fr.morgan.initiativeasso.service.interfaces.UserService;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtServiceTest {

    private JwtService jwtService;
    private UserService userService;
    private JwtConfig jwtConfig;

    private final String SECRET = "ZmFrZVNlY3JldEtleUZvclRlc3RTaG91bGRCZUxvbmc="; // clé base64 fictive
    private final long EXPIRATION = 1000 * 60 * 60; // 1h

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        jwtConfig = mock(JwtConfig.class);

        when(jwtConfig.getSecret()).thenReturn(SECRET);
        when(jwtConfig.getExpiration()).thenReturn(EXPIRATION);

        jwtService = new JwtService(userService, jwtConfig);
    }

    @Test
    void testGenerateToken() {
        User user = new Porteur();
        user.setId(1L);
        user.setNom("Dupont");
        user.setPrenom("Jean");
        user.setEmail("jean.dupont@example.com");
        user.setPassword("password");
        user.setRoles(List.of(UserRole.PORTEUR));
        user.setFirstLogin(true);

        when(userService.loadUserByUsername("jean.dupont@example.com")).thenReturn(user);

        Map<String, String> result = jwtService.generate("jean.dupont@example.com");

        assertNotNull(result);
        assertTrue(result.containsKey("bearer"));

        String token = result.get("bearer");
        assertNotNull(token);

        // Vérification que le username est bien inclus
        String extractedUsername = jwtService.extractUsername(token);
        assertEquals("jean.dupont@example.com", extractedUsername);
    }

    @Test
    void testExtractUsername() {
        // Générer un token
        User user = new Porteur();
        user.setEmail("alice@example.com");

        when(userService.loadUserByUsername("alice@example.com")).thenReturn(user);
        String token = jwtService.generate("alice@example.com").get("bearer");

        String extracted = jwtService.extractUsername(token);
        assertEquals("alice@example.com", extracted);
    }

    @Test
    void testIsTokenExpired_ShouldReturnFalse_WhenTokenIsValid() {
        User user = new Porteur();
        user.setEmail("bob@example.com");
        when(userService.loadUserByUsername("bob@example.com")).thenReturn(user);

        String token = jwtService.generate("bob@example.com").get("bearer");

        assertFalse(jwtService.isTokenExpired(token));
    }

    @Test
    void testIsTokenExpired_ShouldReturnTrue_WhenTokenIsExpired() throws InterruptedException {
        // 1. Configurer expiration très courte pour le test
        when(jwtConfig.getExpiration()).thenReturn(50L); // 50 ms
        jwtService = new JwtService(userService, jwtConfig);

        // 2. Créer un utilisateur concret
        Porteur user = new Porteur();
        user.setEmail("expired@example.com");
        when(userService.loadUserByUsername("expired@example.com")).thenReturn(user);

        // 3. Générer le token
        String token = jwtService.generate("expired@example.com").get("bearer");

        // 4. Attendre que le token expire
        Thread.sleep(100); // > 50ms pour être sûr que le token est expiré

        // 5. Vérifier l'expiration
        boolean expired = jwtService.isTokenExpired(token);
        assertTrue(expired);
    }


    @Test
    void testGetAllClaims() {
        User user = new SalarieAsso();
        user.setId(99L);
        user.setNom("Martin");
        user.setPrenom("Paul");
        user.setEmail("paul.martin@example.com");
        user.setRoles(List.of(UserRole.ADMIN));

        when(userService.loadUserByUsername("paul.martin@example.com")).thenReturn(user);

        String token = jwtService.generate("paul.martin@example.com").get("bearer");
        Claims claims = jwtService.getAllClaims(token);

        assertEquals("paul.martin@example.com", claims.getSubject());
        assertEquals(99, claims.get("id"));
        assertEquals("Martin", claims.get("nom"));
        assertEquals("Paul", claims.get("prenom"));
    }
}
