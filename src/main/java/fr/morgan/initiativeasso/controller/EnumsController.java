package fr.morgan.initiativeasso.controller;

import fr.morgan.initiativeasso.model.enums.SecteursReseaux;
import fr.morgan.initiativeasso.model.enums.UserRole;
import fr.morgan.initiativeasso.model.enums.ZonesDeplacement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enums")
public class EnumsController {

    @GetMapping("/roles")
    public UserRole[] getRoles() {
        return UserRole.values();
    }

    @GetMapping("/activites")
    public SecteursReseaux[] getActivites() {
        return SecteursReseaux.values();
    }

    @GetMapping("/zonesdeplacement")
    public ZonesDeplacement[] getZonesDeplacement() {return ZonesDeplacement.values();}
}
