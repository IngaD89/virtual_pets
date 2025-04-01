package com.example.virtual_pets.services;

import com.example.virtual_pets.models.Pet;
import com.example.virtual_pets.repositories.PetRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetEnergyRecoveryService {

    private final PetRepository petRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public PetEnergyRecoveryService(
            PetRepository petRepository,
            SimpMessagingTemplate messagingTemplate
    ) {
        this.petRepository = petRepository;
        this.messagingTemplate = messagingTemplate;
    }
    @Scheduled(fixedRate = 6000) // Corre cada 60 segundos
    public List<Pet> recoverEnergyForAllPets() {
        List<Pet> pets = petRepository.findAll();
        for (Pet pet : pets) {
            if (pet.getEnergyLevel() < pet.getMaxEnergy()) {
                int energyIncrease = (pet.getHungerLevel() < 20) ? 2 : 5;
                pet.setEnergyLevel(Math.min(pet.getEnergyLevel() + energyIncrease, pet.getMaxEnergy()));
            }
        }
        petRepository.saveAll(pets);
        messagingTemplate.convertAndSend("/topic/petUpdates", pets);
        return pets;
    }


}

