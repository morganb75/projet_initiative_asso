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
import java.util.Optional;
import java.util.function.Function;

import org.springframework.stereotype.Service;

@Service
public class JwtService {

    //généré sur encryption key generator de RandomGenerate.io
    private final String ENCRYPTION_KEY = "a786d7b256d30a6cd6cd560123197e2cc18bf09218ef78423c4aab9a049a1573";
    private final UserService userService;

    public JwtService(UserService userService) {
        this.userService = userService;
    }

    public Map<String, String> generate(String username) {
        Optional<User> user = userService.findByEmail(username);
        return generateJwt(user.get());
    }

    public String extractUsername(String token) {
        return this.getClaim(token, Claims::getSubject);
    }

    public String extractRole(String token) {
        Claims claims = getAllClaims(token);
        return claims.get("role", String.class);
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = this.getClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }

    private Date getExpirationDate(String token) {
        return this.getClaim(token, Claims::getExpiration);
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

    private Map<String, String> generateJwt(User user) {
        final long currentTime = System.currentTimeMillis();
        final long expirationTime = currentTime + 30 * 60 * 1000;

        final Map<String, Object> claims = Map.of(
                "role", user.getRole(),
                Claims.EXPIRATION, new Date(expirationTime),
                Claims.SUBJECT, user.getUsername()
        );

        final String bearer = Jwts.builder()
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expirationTime))
                .setSubject(user.getUsername())
                .setClaims(claims)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();

        return Map.of("bearer", bearer);
    }

    private Key getKey() {
        final byte[] decoder = Decoders.BASE64.decode(ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(decoder);
    }
}


