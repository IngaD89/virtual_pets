package com.example.virtual_pets.exceptions.authExceptios;

public class UnauthorizedAccessException extends RuntimeException{
    public UnauthorizedAccessException() {
        super("Unauthorized Access Exception");
    }

    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
