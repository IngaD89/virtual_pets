package com.example.virtual_pets.controllers;

import com.example.virtual_pets.dto.PetRequest;
import com.example.virtual_pets.models.Pet;
import com.example.virtual_pets.services.PetService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pets")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
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
                petRequest.ownerId(),
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
}
