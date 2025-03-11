package com.example.virtual_pets.repositories;

import com.example.virtual_pets.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {
}
