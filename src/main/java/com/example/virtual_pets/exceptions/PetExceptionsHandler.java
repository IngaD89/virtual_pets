package com.example.virtual_pets.exceptions;

import com.example.virtual_pets.common.utils.ErrorResponseUtils;
import com.example.virtual_pets.dto.ErrorResponse;
import com.example.virtual_pets.exceptions.petExceptions.EmptyPetListException;
import com.example.virtual_pets.exceptions.petExceptions.InvalidPetNameException;
import com.example.virtual_pets.exceptions.petExceptions.PetAlreadyDeletedException;
import com.example.virtual_pets.exceptions.petExceptions.PetNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PetExceptionsHandler {

    private String getMessage(Exception e){
        String response;
        if(e instanceof EmptyPetListException){
            response = e.getMessage();
        } else if (e instanceof PetAlreadyDeletedException) {
            response = e.getMessage();
        } else if (e instanceof InvalidPetNameException) {
            response = e.getMessage();
        }else {
            response = "An unknown error occurred";
        }
        return response;
    }

    @ExceptionHandler({
            EmptyPetListException.class,
            PetAlreadyDeletedException.class,
            InvalidPetNameException.class
    })
    public ResponseEntity<ErrorResponse> handelBadRequest(Exception e){
        String message = getMessage(e);
        return ErrorResponseUtils.createErrorResponse(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(PetNotFoundException.class)
    public ResponseEntity<ErrorResponse> handler(PetNotFoundException e) {
        return ErrorResponseUtils.createErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }
}
