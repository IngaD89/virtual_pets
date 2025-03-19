package com.example.virtual_pets.exceptions.petExceptions;

public class PetNotFoundException extends RuntimeException{
    public PetNotFoundException() {
        super("Pet Not found");
    }

    public PetNotFoundException(String message) {
        super(message);
    }
}
