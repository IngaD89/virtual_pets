package com.example.virtual_pets.exceptions.petExceptions;

public class TooTiredPetException extends RuntimeException{
    public TooTiredPetException() {
        super("Your pet doesn’t have enough energy to play. To gain energy, feed your pet.");
    }
}
