package com.example.virtual_pets.dto;

import java.time.Instant;

public record ErrorResponse(
        int statusCode,
        String message,
        Instant timestamp
) {

}

