package fr.morgan.initiativeasso.controller;

import fr.morgan.initiativeasso.config.JwtService;
import fr.morgan.initiativeasso.model.dto.AuthenticationDto;
import fr.morgan.initiativeasso.service.interfaces.UserService;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/connexion")
    public Map<String, String> connexion(@RequestBody AuthenticationDto authenticationDto) {
        final Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationDto.email(), authenticationDto.password()));
        if (authenticate.isAuthenticated()) {
            log.info("connexion OK - user authentifié: {}", authenticationDto.email());
            return jwtService.generate(authenticationDto.email());
        }
        return null;
    }
}
