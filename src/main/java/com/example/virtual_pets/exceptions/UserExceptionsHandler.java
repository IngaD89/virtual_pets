package com.example.virtual_pets.exceptions;

import com.example.virtual_pets.common.utils.ErrorResponseUtils;
import com.example.virtual_pets.dto.ErrorResponse;

import com.example.virtual_pets.exceptions.userExceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionsHandler {
    @ExceptionHandler({UserAlreadyDeletedException.class, UserRoleNotMatchException.class})
    public ResponseEntity<ErrorResponse> badRequestHandler(RuntimeException e) {
        if (e instanceof UserAlreadyDeletedException) {
            return ErrorResponseUtils.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } else if (e instanceof UserRoleNotMatchException) {
            return ErrorResponseUtils.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ErrorResponseUtils.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unknown error occurred.");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handler(UserNotFoundException e) {
        return ErrorResponseUtils.createErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler({NicknameAlreadyInUseException.class, EmailAlreadyInUseException.class})
    public ResponseEntity<ErrorResponse> conflictHandler(RuntimeException e) {
        if (e instanceof NicknameAlreadyInUseException) {
            return ErrorResponseUtils.createErrorResponse(HttpStatus.CONFLICT, e.getMessage());
        } else if (e instanceof EmailAlreadyInUseException) {
            return ErrorResponseUtils.createErrorResponse(HttpStatus.CONFLICT, e.getMessage());
        }
        return ErrorResponseUtils.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unknown error occurred.");
    }

}
