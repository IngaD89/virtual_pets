package com.example.virtual_pets.exceptions;

public class NicknameAlreadyInUseException extends RuntimeException{

    public NicknameAlreadyInUseException() {
        super("Nickname already in use");
    }

    public NicknameAlreadyInUseException(String message) {
        super(message);
    }
}
