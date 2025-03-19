package com.example.virtual_pets.exceptions.authExceptios;

public class SessionAlreadyInUseException extends RuntimeException{
    public SessionAlreadyInUseException() {
        super("Session already in use");
    }
}
