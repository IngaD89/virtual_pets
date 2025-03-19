package com.example.virtual_pets.exceptions.userExceptions;

public class UserRoleNotMatchException extends RuntimeException{
    public UserRoleNotMatchException() {
        super("Permission denied, user role does not match");
    }
}
