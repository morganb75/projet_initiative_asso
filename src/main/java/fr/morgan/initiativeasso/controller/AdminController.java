package fr.morgan.initiativeasso.controller;

import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.dto.UserDto;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;
import fr.morgan.initiativeasso.service.AdresseService;
import fr.morgan.initiativeasso.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final AdresseService adresseService;

    public AdminController(UserService userService, AdresseService adresseService) {
        this.userService = userService;
        this.adresseService = adresseService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user")
    public void preInscrireUser(@RequestBody UserDto user) {
        userService.preInscriptionUser(user);
    }

    @Operation(summary = "Liste des utilisateurs",
            description = "Remonte la liste complète des utilisateurs de l'application",
            security = @SecurityRequirement(name = "bearer-key"))
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable Long id) throws UserNotFoundException {
        return userService.findById(id);
    }

    @DeleteMapping("/user/{id}")
    public void deleteAccount(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
