package fr.morgan.initiativeasso.config;

import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.service.UserService;
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

    //généré sur encryption key generator de RandomGenerate.io
    private final String ENCRYPTION_KEY = "a786d7b256d30a6cd6cd560123197e2cc18bf09218ef78423c4aab9a049a1573";
    private final UserService userService;
    private final long CURRENT_TIME = System.currentTimeMillis();
//    private final long DUREE_DE_VIE_H_TOKEN = 1;
//    private final long EXPIRATION_TIME =  CURRENT_TIME + DUREE_DE_VIE_H_TOKEN * 60 * 60 * 1000;
    private final long DUREE_DE_VIE_MN_TOKEN = 1;
    private final long EXPIRATION_TIME =  CURRENT_TIME + DUREE_DE_VIE_MN_TOKEN  * 60 * 1000;

    public JwtService(UserService userService) {
        this.userService = userService;
    }


    //1. Génération du Token en allant chercher le user dans la BDD
    public Map<String, String> generate(String username) {
        UserDetails user = userService.loadUserByUsername(username);
        return generateJwt((User) user);
    }

    //2. Forgeage du token
    private Map<String, String> generateJwt(User user) {

        final Map<String, Object> claims = Map.of(
                "nom", user.getNom(),
                "prenom", user.getPrenom(),
                "roles", user.getRoles(),
                Claims.EXPIRATION, new Date(EXPIRATION_TIME),
                Claims.SUBJECT, user.getUsername()
        );

        final String bearer = Jwts.builder()
                .setIssuedAt(new Date(CURRENT_TIME))
                .setExpiration(new Date(EXPIRATION_TIME))
                .setSubject(user.getUsername())
                .setClaims(claims)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();

        return Map.of("bearer", bearer);
    }

    //3. Clé de Cryptage
    private Key getKey() {
        final byte[] decoder = Decoders.BASE64.decode(ENCRYPTION_KEY);
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
                .setSigningKey(this.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}


