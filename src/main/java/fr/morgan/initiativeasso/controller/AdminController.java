package fr.morgan.initiativeasso.controller;

import fr.morgan.initiativeasso.model.Porteur;
import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.dto.UserDto;
import fr.morgan.initiativeasso.model.dto.UserPreinscriptionDto;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;
import fr.morgan.initiativeasso.service.interfaces.AdresseService;
import fr.morgan.initiativeasso.service.interfaces.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService, AdresseService adresseService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user")
    public void preInscrireUser(@RequestBody UserPreinscriptionDto user) {
        userService.preInscriptionUser(user);
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/porteurs")
    public List<UserDto> getAllPorteurs() {return userService.findAllPorteurs();}

    @GetMapping("/users/parrains")
    public List<UserDto> getAllParrains() {return userService.findAllParrains();}

    @GetMapping("/user/{id}")
    public Optional<User> findUserById(@PathVariable Long id) throws UserNotFoundException {
        return userService.findById(id);
    }

    @DeleteMapping("/user/{id}")
    public void deleteAccount(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
