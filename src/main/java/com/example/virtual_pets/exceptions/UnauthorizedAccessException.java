package com.example.virtual_pets.exceptions;

public class UnauthorizedAccessException extends RuntimeException{
    public UnauthorizedAccessException() {
        super("Unauthorized Access Exception");
    }

    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
