package fr.morgan.initiativeasso.exceptions;

import lombok.Builder;

public class UserNotFoundException extends Exception {
    @Builder
    public UserNotFoundException(String message) {
        super(message);
    }
}
