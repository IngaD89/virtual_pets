package com.example.virtual_pets.models;

import com.example.virtual_pets.models.enums.Role;

import java.time.Instant;
import java.util.UUID;

public class User {
    private UUID id;
    private Instant createdAt;
    private Instant updatedAt;
    private Role role;
    private String email;
    private Password password;
    private boolean deleted;
}
