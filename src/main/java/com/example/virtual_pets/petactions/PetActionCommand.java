package com.example.virtual_pets.petactions;

import java.util.UUID;

public interface PetActionCommand {
    void execute(UUID id);
}
