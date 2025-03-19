package com.example.virtual_pets.exceptions.userExceptions;

public class UserAlreadyDeletedException extends RuntimeException{
    public UserAlreadyDeletedException() {
        super("User already deleted");
    }
}
