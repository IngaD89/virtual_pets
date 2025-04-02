package com.example.virtual_pets.services;

import com.example.virtual_pets.models.Pet;
import com.example.virtual_pets.repositories.PetRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetHappinessService {

    private final PetRepository petRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public PetHappinessService(
            PetRepository petRepository,
            SimpMessagingTemplate messagingTemplate
    ) {
        this.petRepository = petRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @Scheduled(fixedRate = 6000) // Corre cada 60 segundos
    public void decreaseHappinessForAllPets() {
        List<Pet> pets = petRepository.findAll();
        for (Pet pet : pets) {
            if (pet.getHappinessLevel() > 0) {
                pet.setHappinessLevel(Math.max(pet.getHappinessLevel() - 5, 0));

            }
        }
        petRepository.saveAll(pets);
        messagingTemplate.convertAndSend("/topic/petUpdates", pets);
    }
}
