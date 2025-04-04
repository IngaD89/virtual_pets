package com.example.virtual_pets.petactions;

import com.example.virtual_pets.exceptions.petExceptions.PetNotFoundException;
import com.example.virtual_pets.models.Pet;
import com.example.virtual_pets.repositories.PetRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class FeedCommand implements PetActionCommand {

    private final PetRepository petRepository;

    public FeedCommand(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Pet execute(UUID id) {
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

        petRepository.save(pet);

        return pet;
    }
}
