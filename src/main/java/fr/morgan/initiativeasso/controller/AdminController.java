package fr.morgan.initiativeasso.controller;

import fr.morgan.initiativeasso.model.dto.UserDto;
import fr.morgan.initiativeasso.service.AdresseService;
import fr.morgan.initiativeasso.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    private final UserService userService;
    private final AdresseService adresseService;

    public AdminController(UserService userService, AdresseService adresseService) {
        this.userService = userService;
        this.adresseService = adresseService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/admin/user")
    public void preInscrireUser(@RequestBody UserDto user){
        userService.preInscriptionUser(user);
    }
}
