package com.example.virtual_pets.controllers;

import com.example.virtual_pets.dto.PetRequest;
import com.example.virtual_pets.exceptions.petExceptions.PetNotFoundException;
import com.example.virtual_pets.exceptions.petExceptions.TooTiredPetException;
import com.example.virtual_pets.models.Pet;
import com.example.virtual_pets.petactions.FeedCommand;
import com.example.virtual_pets.petactions.PlayCommand;
import com.example.virtual_pets.services.PetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pets")
public class PetController {
    private final PetService petService;
    private final FeedCommand feedCommand;
    private final PlayCommand playCommand;

    public PetController(
            PetService petService,
            FeedCommand feedCommand,
            PlayCommand playCommand
    ) {
        this.petService = petService;
        this.feedCommand = feedCommand;
        this.playCommand = playCommand;
    }

    @GetMapping()
    public ResponseEntity<List<Pet>> getAllPets(){
        List<Pet> pets = petService.getAllPets();
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable UUID id){
        Pet pet = this.petService.getPetById(id);
        return ResponseEntity.ok(pet);
    }

    @PostMapping()
    public ResponseEntity<Pet> createPet(@Valid @RequestBody PetRequest petRequest){
        Pet newPet = petService.createPet(
                petRequest.name(),
                petRequest.petCharacter()
        );
        return ResponseEntity.ok(newPet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(
            @PathVariable UUID id,
            @Valid @RequestBody PetRequest petRequest
    ){
        Pet updatedPet =  petService.updatePet(id, petRequest.name());
        return ResponseEntity.ok(updatedPet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pet> deletePet(@PathVariable UUID id){
       Pet deletedPet = petService.delete(id);
        return ResponseEntity.ok(deletedPet);
    }

    @PostMapping("/{id}/play")
    public ResponseEntity<Pet> play(@PathVariable UUID id) {
            Pet updatedPet = playCommand.execute(id);
            return ResponseEntity.ok(updatedPet);  // Devolvemos la mascota actualizada
    }

    @PostMapping("/{id}/feed")
    public ResponseEntity<Pet> feed(@PathVariable UUID id) {
            Pet updatedPet = feedCommand.execute(id);
            return ResponseEntity.ok(updatedPet);  // Devolvemos la mascota actualizada
    }

}
