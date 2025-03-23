package com.example.virtual_pets.petactions;

import com.example.virtual_pets.exceptions.petExceptions.PetNotFoundException;
import com.example.virtual_pets.models.Pet;
import com.example.virtual_pets.repositories.PetRepository;

import java.time.Instant;
import java.util.UUID;

public class FeedCommand implements PetActionCommand {

    private final PetRepository petRepository;

    public FeedCommand(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public void execute(UUID id) {
        Pet pet = this
                .petRepository
                .findById(id)
                .orElseThrow(PetNotFoundException::new);

        if (pet.getHungerLevel() < pet.getMaxHunger()) {
            pet.setHungerLevel(pet.getHungerLevel() + 20);
            if (pet.getHungerLevel() > pet.getMaxHunger()) {
                pet.setHungerLevel(pet.getMaxHunger());
            }
        }

        if (pet.getEnergyLevel() < pet.getMaxEnergy()) {
            pet.setEnergyLevel(pet.getEnergyLevel() + 10);
            if (pet.getEnergyLevel() > pet.getMaxEnergy()) {
                pet.setEnergyLevel(pet.getMaxEnergy());
            }
        }

        pet.setLastFedAt(Instant.now());
        petRepository.save(pet);

    }
}
