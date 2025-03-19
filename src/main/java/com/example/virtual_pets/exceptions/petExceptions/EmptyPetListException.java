package com.example.virtual_pets.exceptions.petExceptions;

public class EmptyPetListException extends RuntimeException{
    public EmptyPetListException() {
        super("No pets was found");
    }

    public EmptyPetListException(String message) {
        super(message);
    }
}
