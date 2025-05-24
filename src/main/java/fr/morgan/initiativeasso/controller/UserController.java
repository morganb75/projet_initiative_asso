package fr.morgan.initiativeasso.controller;

import fr.morgan.initiativeasso.exceptions.ParrainAlreadyExist;
import fr.morgan.initiativeasso.model.Parrain;
import fr.morgan.initiativeasso.model.Porteur;
import fr.morgan.initiativeasso.model.User;
import fr.morgan.initiativeasso.model.dto.ParrainDto;
import fr.morgan.initiativeasso.model.dto.UserDto;
import fr.morgan.initiativeasso.model.exception.UserNotFoundException;
import fr.morgan.initiativeasso.service.interfaces.UserService;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
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
    public UserDto findUserByEmail(@RequestParam String email) throws UserNotFoundException {
        return userService.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Pas de user associé à cet Email!"));
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id) throws UserNotFoundException {
        return userService.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("Pas de User avec cet ID: %d", id)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            User userUpDated = userService.updatedUser(id, user);
            return ResponseEntity.ok(userUpDated);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Utilisateur avec l'ID " + id + " non trouvé! "));
        } catch (HibernateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/{parrainId}")
    public ResponseEntity<?> setParrainToPorteur(@PathVariable Long id, @PathVariable Long parrainId) {
        try {
            return ResponseEntity.ok(userService.setParrain(id,parrainId));
        } catch (UserNotFoundException | ParrainAlreadyExist e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/parrains")
    public List<UserDto> findParrainsForPorteurFeed() {
        return userService.findAllParrains();
    }

    @GetMapping("/parrainid")
    public ResponseEntity<ParrainDto> findParrainIdFromPorteur(Long porteurId) {
        return null;
    }

    @GetMapping("/porteurs")
    public List<UserDto> findPorteursForParrainFeed() {
        return userService.findAllPorteurs();
    }

    @GetMapping("/feed/porteur/{parrainId}")
    public List<UserDto> getFeedUser(@PathVariable Long parrainId) {
        return userService.feedPorteur(parrainId);
    }

}
