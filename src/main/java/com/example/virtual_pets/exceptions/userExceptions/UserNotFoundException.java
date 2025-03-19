package com.example.virtual_pets.exceptions.userExceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("User Not found exception");
    }
}
