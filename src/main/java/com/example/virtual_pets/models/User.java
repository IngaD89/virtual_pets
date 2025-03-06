package com.example.virtual_pets.models;

import com.example.virtual_pets.exceptions.UserAlreadyDeletedException;
import com.example.virtual_pets.exceptions.UserRoleNotMatchException;
import com.example.virtual_pets.models.enums.Role;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

import java.time.Instant;
import java.util.UUID;

@Entity
@Access(AccessType.FIELD)
public class User {
    private UUID id;
    private Instant createdAt;
    private Instant updatedAt;
    private Role role;
    private String nickname;
    private String email;
    @Embedded
    private Password password;
    private boolean deleted;

    public User(
            String nickname,
            String email,
            Password password
    ) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.role = Role.USER_BASIC;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.deleted = false;
    }

    public UUID getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Password getPassword() {
        return password;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void checkIfDeleted() {
        if (this.deleted) {
            throw new UserAlreadyDeletedException();
        }
    }

    public void assertRoleMatches(User user) {
        if (!user.getId().equals(this.id) && !user.getRole().equals(Role.USER_ADMIN)) {
                throw new UserRoleNotMatchException();
            }
        }
}
