package com.example.virtual_pets.exceptions.authExceptios;

public class SessionNotFoundException extends RuntimeException{
    public SessionNotFoundException(String message) {
        super(message);
    }
}
