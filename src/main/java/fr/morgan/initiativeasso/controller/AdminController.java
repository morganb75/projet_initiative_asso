package fr.morgan.initiativeasso.controller;

import fr.morgan.initiativeasso.model.Adresse;
import fr.morgan.initiativeasso.model.UserDto;
import fr.morgan.initiativeasso.model.enums.UserRole;
import fr.morgan.initiativeasso.service.AdresseService;
import fr.morgan.initiativeasso.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AdminController {

    private final UserService userService;
    private final AdresseService adresseService;

    public AdminController(UserService userService, AdresseService adresseService) {
        this.userService = userService;
        this.adresseService = adresseService;
    }

    @PostMapping("/admin/user")
    public void preInscrireUser(@RequestBody UserDto user){
        UserRole role = user.getRole();
        userService.preInscriptionUser(user, role);
    }

}
