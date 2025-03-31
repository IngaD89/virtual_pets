package com.example.virtual_pets.exceptions;

import com.example.virtual_pets.common.utils.ErrorResponseUtils;
import com.example.virtual_pets.dto.ErrorResponse;
import com.example.virtual_pets.exceptions.authExceptios.*;
import com.example.virtual_pets.exceptions.petExceptions.*;
import com.example.virtual_pets.exceptions.userExceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception e) {
        return ErrorResponseUtils.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errorMessage = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("; "));
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                errorMessage.toString(),
                Instant.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            AuthenticationException.class,
            IncorrectCredentialsException.class,
            SessionAlreadyInUseException.class,
            UnauthorizedAccessException.class,
            SessionNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleAuthExceptions(Exception e) {
        if (e instanceof AuthenticationException) {
            return ErrorResponseUtils.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } else if (e instanceof IncorrectCredentialsException) {
            return ErrorResponseUtils.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } else if (e instanceof SessionAlreadyInUseException) {
            return ErrorResponseUtils.createErrorResponse(HttpStatus.CONFLICT, e.getMessage());
        } else if (e instanceof UnauthorizedAccessException) {
            return ErrorResponseUtils.createErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
        } else if (e instanceof SessionNotFoundException) {
            return ErrorResponseUtils.createErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } else {
            return ErrorResponseUtils.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ExceptionHandler({
            EmailAlreadyInUseException.class,
            NicknameAlreadyInUseException.class,
            UserAlreadyDeletedException.class,
            UserNotFoundException.class,
            UserRoleNotMatchException.class
    })
    public ResponseEntity<ErrorResponse> handleUserExceptions(Exception e) {
        if (e instanceof EmailAlreadyInUseException) {
            return ErrorResponseUtils.createErrorResponse(HttpStatus.CONFLICT, e.getMessage());
        } else if (e instanceof NicknameAlreadyInUseException) {
            return ErrorResponseUtils.createErrorResponse(HttpStatus.CONFLICT, e.getMessage());
        } else if (e instanceof UserAlreadyDeletedException) {
            return ErrorResponseUtils.createErrorResponse(HttpStatus.CONFLICT, e.getMessage());
        } else if (e instanceof UserNotFoundException) {
            return ErrorResponseUtils.createErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } else if (e instanceof UserRoleNotMatchException) {
            return ErrorResponseUtils.createErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
        } else {
            return ErrorResponseUtils.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @ExceptionHandler({
            PetAlreadyDeletedException.class,
            EmptyPetListException.class,
            InvalidPetNameException.class,
            PetNotFoundException.class,
            TooTiredPetException.class
    })
    public ResponseEntity<ErrorResponse> handelPetExceptions(Exception e) {
        if (e instanceof PetAlreadyDeletedException) {
            return ErrorResponseUtils.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } else if (e instanceof EmptyPetListException) {
            return ErrorResponseUtils.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } else if (e instanceof InvalidPetNameException) {
            return ErrorResponseUtils.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } else if (e instanceof PetNotFoundException) {
            return ErrorResponseUtils.createErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } else if (e instanceof TooTiredPetException) {
            return ErrorResponseUtils.createErrorResponse(HttpStatus.FORBIDDEN, e.getMessage());
        } else {
            return ErrorResponseUtils.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}


