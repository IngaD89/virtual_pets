package com.example.virtual_pets.exceptions;

public class IncorrectCredentialsException extends RuntimeException{
    public IncorrectCredentialsException() {
        super("Incorrect username or password");
    }
}
