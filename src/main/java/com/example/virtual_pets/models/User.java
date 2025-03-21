package com.example.virtual_pets.models;

import com.example.virtual_pets.exceptions.userExceptions.UserAlreadyDeletedException;
import com.example.virtual_pets.exceptions.userExceptions.UserRoleNotMatchException;
import com.example.virtual_pets.models.enums.Role;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import java.sql.Types;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(unique = true, nullable = false, length = 255)
    private String email;

    @Column(unique = true, nullable = false, length = 50)
    private String nickname;

    @Embedded
    private Password password;

    @Column(nullable = false)
    private boolean deleted;

    public User() {
    }

    public User(
            String email,
            String nickname,
            Password password
    ) {
        //this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.role = Role.USER_BASIC;
        this.email = email;
        this.nickname = nickname;
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
