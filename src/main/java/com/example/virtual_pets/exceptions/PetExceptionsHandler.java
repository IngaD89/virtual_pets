package com.example.virtual_pets.exceptions;

import com.example.virtual_pets.common.utils.ErrorResponseUtils;
import com.example.virtual_pets.dto.ErrorResponse;
import com.example.virtual_pets.exceptions.petExceptions.EmptyPetListException;
import com.example.virtual_pets.exceptions.petExceptions.PetAlreadyDeletedException;
import com.example.virtual_pets.exceptions.petExceptions.PetNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PetExceptionsHandler {

    @ExceptionHandler({EmptyPetListException.class, PetAlreadyDeletedException.class})
    public ResponseEntity<ErrorResponse> handler(Exception e){
        if (e instanceof EmptyPetListException) {
            return ErrorResponseUtils.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } else if (e instanceof PetAlreadyDeletedException) {
            return ErrorResponseUtils.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ErrorResponseUtils.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unknown error occurred.");
    }

    @ExceptionHandler(PetNotFoundException.class)
    public ResponseEntity<ErrorResponse> handler(PetNotFoundException e) {
        return ErrorResponseUtils.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
