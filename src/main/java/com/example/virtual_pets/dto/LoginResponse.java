package com.example.virtual_pets.dto;

import java.util.UUID;

public record LoginResponse(UUID sessionId, String token) {

}

