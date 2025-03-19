package com.example.virtual_pets.exceptions.petExceptions;

public class PetAlreadyDeletedException extends RuntimeException{
    public PetAlreadyDeletedException() {
        super("Pet already deleted");
    }

    public PetAlreadyDeletedException(String message) {
        super(message);
    }
}
