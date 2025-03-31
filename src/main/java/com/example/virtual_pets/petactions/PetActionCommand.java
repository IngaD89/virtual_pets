package com.example.virtual_pets.petactions;

import com.example.virtual_pets.models.Pet;

import java.util.UUID;

public interface PetActionCommand {
    Pet execute(UUID id);
}
