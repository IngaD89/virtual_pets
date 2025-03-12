package com.example.virtual_pets.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("User Not found exception");
    }
}
