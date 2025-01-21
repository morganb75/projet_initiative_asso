package fr.morgan.initiativeasso.config;

import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.service.interfaces.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    //***********************PHASE DE FORGE DU TOKEN**********************************

    private final UserService userService;
    private final JwtConfig jwtConfig;

    public JwtService(UserService userService, JwtConfig jwtConfig) {
        this.userService = userService;
        this.jwtConfig = jwtConfig;
    }

    //1. Génération du Token en allant chercher le user dans la BDD
    public Map<String, String> generate(String username) {
        UserDetails user = userService.loadUserByUsername(username);
        return generateJwt((User) user);
    }

    //2. Forgeage du token
    private Map<String, String> generateJwt(User user) {
        final long CURRENT_TIME = System.currentTimeMillis();

        final Map<String, Object> claims = Map.of(
                "nom", user.getNom(),
                "prenom", user.getPrenom(),
                "roles", user.getRoles(),
                "firstLogin", user.getFirstLogin(),
                Claims.EXPIRATION, new Date(CURRENT_TIME + jwtConfig.getExpiration()),
                Claims.SUBJECT, user.getUsername()
        );

        final String bearer = Jwts.builder()
                .setIssuedAt(new Date(CURRENT_TIME))
                .setExpiration(new Date(CURRENT_TIME + jwtConfig.getExpiration()))
                .setSubject(user.getUsername())
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecret())
                .compact();

        return Map.of("bearer", bearer);
    }

    //3. Clé de Cryptage
    private Key getKey() {
        final byte[] decoder = Decoders.BASE64.decode(jwtConfig.getSecret());
        return Keys.hmacShaKeyFor(decoder);
    }

    //************************************************************************************
    public String extractUsername(String token) {
        return this.getClaim(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = this.getClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }

    private <T> T getClaim(String token, Function<Claims, T> function) {
        Claims claims = getAllClaims(token);
        return function.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtConfig.getSecret())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}


