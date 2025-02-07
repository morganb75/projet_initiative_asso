package fr.morgan.initiativeasso.model.exception;

import lombok.Builder;

public class ExistingLikeException extends Exception{

    @Builder
    public ExistingLikeException(String message) {
        super(message);
    }
}
