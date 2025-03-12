package com.example.virtual_pets.exceptions;

public class SessionAlreadyInUseException extends RuntimeException{
    public SessionAlreadyInUseException() {
        super("Session already in use");
    }
}
