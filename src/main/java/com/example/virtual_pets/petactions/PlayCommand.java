package com.example.virtual_pets.petactions;

import com.example.virtual_pets.exceptions.petExceptions.PetNotFoundException;
import com.example.virtual_pets.exceptions.petExceptions.TooTiredPetException;
import com.example.virtual_pets.models.Pet;
import com.example.virtual_pets.repositories.PetRepository;

import java.util.UUID;

public class PlayCommand implements PetActionCommand {

    private final PetRepository petRepository;

    public PlayCommand(PetRepository petRepository) {
        this.petRepository = petRepository;
    }


    @Override
    public void execute(UUID id) {
        Pet pet = petRepository
                .findById(id)
                .orElseThrow(PetNotFoundException::new);

        if (pet.getEnergyLevel() == 0) {
            throw new TooTiredPetException();
        }

        if (pet.getEnergyLevel() > 0) {
            pet.setEnergyLevel(Math.max(pet.getEnergyLevel() - 10, 0));
            pet.setHappinessLevel(Math.min(pet.getHappinessLevel() + 20, pet.getMaxHappiness()));
        }

        petRepository.save(pet);

    }
}
