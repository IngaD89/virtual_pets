package com.example.virtual_pets.models;

import com.example.virtual_pets.models.enums.PetCharacter;
import com.example.virtual_pets.models.enums.PetStatus;
import com.example.virtual_pets.models.enums.PetType;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.Instant;
import java.util.UUID;


//TODO no funciona lombok

@Entity
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;
    @JdbcTypeCode(Types.VARCHAR)
    @Column(name = "owner_id", nullable = false)
    private UUID ownerId;
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
    @Column(name = "last_fed_at", nullable = false)
    private Instant lastFedAt;
    @Column(name = "last_played_at", nullable = false)
    private Instant lastPlayedAt;
    @Enumerated(EnumType.STRING)
    @Column(name = "pet_status", nullable = false)
    private PetStatus petStatus;
    @Enumerated(EnumType.STRING)
    @Column(name = "pet_character", nullable = false)
    private PetCharacter petCharacter;
    @Enumerated(EnumType.STRING)
    @Column(name = "pet_type", nullable = false)
    private PetType petType;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(name = "energy_level", nullable = false)
    private int energyLevel;
    @Column(name = "hunger_level", nullable = false)
    private int hungerLevel;
    @Column(name = "playfulness_level", nullable = false)
    private int playfulnessLevel;
    @Column(name = "max_energy", nullable = false)
    private int maxEnergy;
    @Column(name = "max_hunger", nullable = false)
    private int maxHunger;
    @Column(name = "max_playfulness", nullable = false)
    private int maxPlayfulness;
    @Column(nullable = false)
    private boolean deleted;


    public Pet() {
    }

    public Pet(UUID ownerId, String name, PetCharacter petCharacter) {
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
