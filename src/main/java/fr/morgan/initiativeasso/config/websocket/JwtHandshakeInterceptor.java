package fr.morgan.initiativeasso.config.websocket;

import fr.morgan.initiativeasso.config.jwt.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

@Slf4j
public class JwtHandshakeInterceptor implements HandshakeInterceptor {

    private final JwtService jwtService;

    public JwtHandshakeInterceptor(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
            WebSocketHandler wsHandler, Map<String, Object> attributes) {

        if (!(request instanceof ServletServerHttpRequest servletRequest)) {
            return true;
        }

        HttpServletRequest httpRequest = servletRequest.getServletRequest();
        String token = extractToken(httpRequest);

        if (token != null) {
            try {
                Claims claims = jwtService.getAllClaims(token);
                String username = claims.getSubject();
                Principal principal = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());

                log.info("✅ WebSocket Handshake valid for user: {}", username);

                // Ajoute l'utilisateur (principal) dans les attributs de session
//                attributes.put("user", (Principal) () -> username);
                attributes.put("principal",  principal);
            } catch (Exception e) {
                log.warn("❌ WebSocket Handshake failed: invalid or expired JWT", e);
                return false;
            }
        } else {
            log.warn("❌ WebSocket Handshake failed: missing JWT");
            return false;
        }

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
            WebSocketHandler wsHandler, Exception exception) {
        // rien à faire ici
    }

    private String extractToken(HttpServletRequest request) {
        // 1. Depuis un paramètre d’URL : ws://localhost:8080/api/ws?token=xxxx
        String token = request.getParameter("token");
        // 2. (optionnel) Depuis un header Authorization : Bearer xxx
        if (token == null) {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
            }
        }
        return token;
    }
}
