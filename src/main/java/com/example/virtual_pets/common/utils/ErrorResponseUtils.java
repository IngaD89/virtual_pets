package com.example.virtual_pets.common.utils;

import com.example.virtual_pets.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

public class ErrorResponseUtils {
    public static ResponseEntity<ErrorResponse> createErrorResponse(HttpStatus status, String message) {
        ErrorResponse errorResponse = new ErrorResponse(
                status.value(),
                message,
                Instant.now()
        );
        return new ResponseEntity<>(errorResponse, status);
    }
}
