package com.example.virtual_pets.dto;

import com.example.virtual_pets.models.enums.PetCharacter;

import java.util.UUID;

public record PetRequest(String name, PetCharacter petCharacter) {
}
