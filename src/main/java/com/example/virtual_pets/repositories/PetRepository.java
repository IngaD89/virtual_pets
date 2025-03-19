package com.example.virtual_pets.repositories;

import com.example.virtual_pets.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PetRepository extends JpaRepository<Pet, UUID> {
    public List<Pet> findByOwnerId(UUID ownerId);
}
