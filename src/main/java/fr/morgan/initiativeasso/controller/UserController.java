package fr.morgan.initiativeasso.controller;

import fr.morgan.initiativeasso.model.Parrain;
import fr.morgan.initiativeasso.model.Porteur;
import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.enums.UserRole;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;
import fr.morgan.initiativeasso.service.interfaces.UserService;

import java.util.List;

import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public User findUserByEmail(@RequestParam String email) throws UserNotFoundException {
        return userService.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Pas de user associé à cet Email!"));
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id) throws UserNotFoundException {
        return userService.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("Pas de User avec cet ID: %d", id)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) throws UserNotFoundException {
        User userUpDated = userService.updatedUser(id, user);
        return ResponseEntity.ok(userUpDated);
    }

    @GetMapping("/parrains")
    public List<Parrain> findParrainsForPorteurFeed() {
        return userService.findAllParrains();
    }

    @GetMapping("/porteurs")
    public List<Porteur> findPorteursForParrainFeed() {
        return userService.findAllPorteurs();
    }
}
