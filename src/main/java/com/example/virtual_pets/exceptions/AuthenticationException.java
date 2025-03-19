package com.example.virtual_pets.exceptions;

public class AuthenticationException extends RuntimeException{
    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException() {
        super("An error has occurred during authentication");
    }
}
