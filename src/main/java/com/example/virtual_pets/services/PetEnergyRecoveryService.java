package com.example.virtual_pets.services;

import com.example.virtual_pets.models.Pet;
import com.example.virtual_pets.repositories.PetRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetEnergyRecoveryService {

    private final PetRepository petRepository;

    public PetEnergyRecoveryService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Scheduled(fixedRate = 60000) // Corre cada 60 segundos
    public void recoverEnergyForAllPets() {
        List<Pet> pets = petRepository.findAll();
        for (Pet pet : pets) {
            if (pet.getEnergyLevel() < pet.getMaxEnergy()) {
                pet.setEnergyLevel(Math.min(pet.getEnergyLevel() + 5, pet.getMaxEnergy()));
            }
        }
        petRepository.saveAll(pets);
    }
}

