package com.example.virtual_pets.models;

import com.example.virtual_pets.models.enums.PetCharacter;
import com.example.virtual_pets.models.enums.PetStatus;
import com.example.virtual_pets.models.enums.PetType;

import java.time.Instant;
import java.util.UUID;

public class Pet {
    //@GeneratedValue(generator = "UUID")
    private UUID id;
    private UUID ownerId;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant lastFedAt;
    private Instant lastPlayedAt;
    private PetStatus petStatus;
    private PetCharacter petCharacter;
    private PetType petType;
    private String name;
    private int energyLevel;
    private int hungerLevel;
    private int playfulnessLevel;
    private int maxEnergy = 100;
    private int maxHunger = 100;
    private int maxPlayfulness = 100;
    private boolean deleted;

    public Pet(UUID ownerId, String name, PetCharacter petCharacter) {
        //this.id = UUID.randomUUID();
        this.ownerId = ownerId;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.lastFedAt = Instant.now();
        this.lastPlayedAt = Instant.now();
        this.petStatus = PetStatus.NEWBORN;
        this.petCharacter = petCharacter;
        this.petType = PetType.YELLOW;
        this.name = name;
        this.energyLevel = 100;
        this.hungerLevel = 100;
        this.playfulnessLevel = 100;
        this.maxEnergy = 100;
        this.maxHunger = 100;
        this.maxPlayfulness = 100;
        this.deleted = false;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getLastFedAt() {
        return lastFedAt;
    }

    public void setLastFedAt(Instant lastFedAt) {
        this.lastFedAt = lastFedAt;
    }

    public Instant getLastPlayedAt() {
        return lastPlayedAt;
    }

    public void setLastPlayedAt(Instant lastPlayedAt) {
        this.lastPlayedAt = lastPlayedAt;
    }

    public PetStatus getPetStatus() {
        return petStatus;
    }

    public void setPetStatus(PetStatus petStatus) {
        this.petStatus = petStatus;
    }

    public PetCharacter getPetCharacter() {
        return petCharacter;
    }

    public void setPetCharacter(PetCharacter petCharacter) {
        this.petCharacter = petCharacter;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }

    public int getHungerLevel() {
        return hungerLevel;
    }

    public void setHungerLevel(int hungerLevel) {
        this.hungerLevel = hungerLevel;
    }

    public int getPlayfulnessLevel() {
        return playfulnessLevel;
    }

    public void setPlayfulnessLevel(int playfulnessLevel) {
        this.playfulnessLevel = playfulnessLevel;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public int getMaxHunger() {
        return maxHunger;
    }

    public void setMaxHunger(int maxHunger) {
        this.maxHunger = maxHunger;
    }

    public int getMaxPlayfulness() {
        return maxPlayfulness;
    }

    public void setMaxPlayfulness(int maxPlayfulness) {
        this.maxPlayfulness = maxPlayfulness;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
