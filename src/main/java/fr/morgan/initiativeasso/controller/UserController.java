package fr.morgan.initiativeasso.controller;

import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;
import fr.morgan.initiativeasso.service.interfaces.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
