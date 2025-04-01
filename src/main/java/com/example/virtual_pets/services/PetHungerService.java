package com.example.virtual_pets.services;

import com.example.virtual_pets.models.Pet;
import com.example.virtual_pets.repositories.PetRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetHungerService {

    private final PetRepository petRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public PetHungerService(
            PetRepository petRepository,
            SimpMessagingTemplate messagingTemplate
    ) {
        this.petRepository = petRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @Scheduled(fixedRate = 6000) // Corre cada 60 segundos
    public void decreaseHungerForAllPets() {
        List<Pet> pets = petRepository.findAll();
        for (Pet pet : pets) {
            if (pet.getHungerLevel() > 0) {
                pet.setHungerLevel(Math.max(pet.getHungerLevel() - 5, 0));

            }
        }
        petRepository.saveAll(pets);
        messagingTemplate.convertAndSend("/topic/petUpdates", pets);
    }

}

