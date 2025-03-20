package com.example.virtual_pets.exceptions.petExceptions;

public class InvalidPetNameException extends RuntimeException{
    public InvalidPetNameException() {
        super("Invalid or empty pet name");
    }

    public InvalidPetNameException(String message) {
        super(message);
    }
}
