package fr.morgan.initiativeasso.model.dto;

// Nul besoin de mettre les getter et setter car c'est un record
public record AuthenticationDto(String email, String password) {
}
