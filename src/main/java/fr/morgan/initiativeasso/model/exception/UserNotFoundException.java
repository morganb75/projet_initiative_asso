package fr.morgan.initiativeasso.model.exception;

import lombok.Builder;

public class UserNotFoundException extends Exception{
    @Builder
    public UserNotFoundException(String message) {
        super(message);
    }
}
