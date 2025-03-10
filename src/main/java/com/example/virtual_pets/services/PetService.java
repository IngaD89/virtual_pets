package com.example.virtual_pets.services;

import com.example.virtual_pets.models.Pet;
import com.example.virtual_pets.models.enums.PetCharacter;
import com.example.virtual_pets.models.enums.PetType;
import com.example.virtual_pets.repositories.PetRepository;
import com.example.virtual_pets.repositories.UserRepository;

import java.util.UUID;

public class PetService {

    private final UserRepository userRepository;
    private final PetRepository petRepository;

    public PetService(UserRepository userRepository, PetRepository petRepository) {
        this.userRepository = userRepository;
        this.petRepository = petRepository;
    }

}
