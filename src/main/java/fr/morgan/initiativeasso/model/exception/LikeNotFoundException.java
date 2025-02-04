package fr.morgan.initiativeasso.model.exception;

import lombok.Builder;

public class LikeNotFoundException extends Exception{
    @Builder
    public LikeNotFoundException(String message) {
        super(message);
    }
}
