package com.example.virtual_pets.exceptions;

import com.example.virtual_pets.common.utils.ErrorResponseUtils;
import com.example.virtual_pets.dto.ErrorResponse;
import com.example.virtual_pets.exceptions.authExceptios.AuthenticationException;
import com.example.virtual_pets.exceptions.authExceptios.IncorrectCredentialsException;
import com.example.virtual_pets.exceptions.authExceptios.SessionAlreadyInUseException;
import com.example.virtual_pets.exceptions.authExceptios.UnauthorizedAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class AuthExceptionsHandler {

    @ExceptionHandler({AuthenticationException.class, IncorrectCredentialsException.class})
    public ResponseEntity<ErrorResponse> handler(RuntimeException e) {
        if (e instanceof AuthenticationException) {
            return ErrorResponseUtils.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } else if (e instanceof IncorrectCredentialsException) {
            return ErrorResponseUtils.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ErrorResponseUtils.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unknown error occurred");
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedAccess(UnauthorizedAccessException e) {
        return ErrorResponseUtils.createErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler(SessionAlreadyInUseException.class)
    public ResponseEntity<ErrorResponse> handleSessionInUse(SessionAlreadyInUseException e) {
        return ErrorResponseUtils.createErrorResponse(HttpStatus.CONFLICT, e.getMessage());
    }
}
