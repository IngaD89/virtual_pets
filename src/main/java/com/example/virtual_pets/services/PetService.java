package com.example.virtual_pets.services;

import com.example.virtual_pets.exceptions.authExceptios.UnauthorizedAccessException;
import com.example.virtual_pets.exceptions.petExceptions.EmptyPetListException;
import com.example.virtual_pets.exceptions.petExceptions.InvalidPetNameException;
import com.example.virtual_pets.exceptions.petExceptions.PetAlreadyDeletedException;
import com.example.virtual_pets.exceptions.petExceptions.PetNotFoundException;
import com.example.virtual_pets.exceptions.userExceptions.UserNotFoundException;
import com.example.virtual_pets.models.Pet;
import com.example.virtual_pets.models.User;
import com.example.virtual_pets.models.enums.PetCharacter;
import com.example.virtual_pets.repositories.PetRepository;
import com.example.virtual_pets.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class PetService {

    private final UserRepository userRepository;
    private final PetRepository petRepository;
    private final AuthenticationService authenticationService;

    public PetService(
            UserRepository userRepository,
            PetRepository petRepository,
            AuthenticationService authenticationService
    ) {
        this.userRepository = userRepository;
        this.petRepository = petRepository;
        this.authenticationService = authenticationService;
    }


    public Pet createPet(
            String name,
            PetCharacter petCharacter
    ) {
        UUID ownerId = authenticationService.getAuthenticatedUserId();

        if (ownerId == null) {
            throw new UnauthorizedAccessException("User must be authenticated to create a pet");
        }

        User petOwner = this.userRepository
                .findById(ownerId)
                .orElseThrow(UserNotFoundException::new);

        Pet newPet = new Pet(petOwner.getId(), name, petCharacter);

        return this.petRepository.save(newPet);
    }


    public Pet getPetById(UUID petId) {
        return this.petRepository.findById(petId)
                .orElseThrow(() -> new PetNotFoundException(
                        String.format("Pet by id %s not found", petId)
                ));
    }

    public List<Pet> getAllPets() {
        String userRole = authenticationService.getAuthenticatedUserRole();
        UUID userId = authenticationService.getAuthenticatedUserId();

        if (authenticationService.isAdmin(userRole.replace("ROLE_", ""))) {
            List<Pet> allPets = this.petRepository
                    .findAll()
                    .stream()
                    .filter(pet -> !pet.isDeleted())
                    .toList();
            return allPets.isEmpty() ? Collections.emptyList() : allPets;
        }
        List<Pet> userPets = this.petRepository.findByOwnerId(userId)
                .stream()
                .filter(pet -> !pet.isDeleted())
                .toList();
        return userPets.isEmpty() ? Collections.emptyList() : userPets;
    }

    public Pet updatePet(
            UUID petId,
            String name
    ) {
        String userRole = authenticationService.getAuthenticatedUserRole();
        UUID userId = authenticationService.getAuthenticatedUserId();

        Pet pet = this.petRepository
                .findById(petId)
                .orElseThrow(PetNotFoundException::new);

        if (!authenticationService.isAdmin(userRole.replace("ROLE_", ""))
                && !pet.getOwnerId().equals(userId)) {
            throw new UnauthorizedAccessException("User does not have permission to update this pet");
        }

        if (pet.getName().equals(name)) {
            return pet;
        }

        if (name != null && !name.trim().isEmpty()) {
            pet.setName(name);
            pet.setUpdatedAt(Instant.now());
            return this.petRepository.save(pet);
        } else {
            throw new InvalidPetNameException("Pet name can not be empty");
        }

    }

    public Pet delete(UUID petId) {
        String userRole = authenticationService.getAuthenticatedUserRole();
        UUID userId = authenticationService.getAuthenticatedUserId();

        Pet pet = this.petRepository
                .findById(petId)
                .orElseThrow(PetNotFoundException::new);

        if (!authenticationService.isAdmin(userRole.replace("ROLE_", ""))
                && !pet.getOwnerId().equals(userId)) {
            throw new UnauthorizedAccessException("User does not have permission to delete this pet");
        }

        if (pet.isDeleted()) {
            throw new PetAlreadyDeletedException("Pet with id " + pet.getId() + " already deleted");
        }
        pet.setDeleted(true);
        pet.setUpdatedAt(Instant.now());
        return this.petRepository.save(pet);
    }
}


