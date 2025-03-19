package com.example.virtual_pets.models;


import com.example.virtual_pets.common.utils.PasswordUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Arrays;

@Embeddable
public class Password {

    @Column(name = "password_salt")
    private byte[] salt;

    @Column(name = "password_hash")
    private byte[] hash;


    public Password() {
    }

    public Password(byte[] salt, byte[] hash) {
        this.salt = salt;
        this.hash = hash;
    }

    public Password(String plainTextPassword) {
        this.salt = PasswordUtils.generateSalt();
        this.hash = PasswordUtils.generateHash(plainTextPassword, this.salt);
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public void setHash(byte[] hash) {
        this.hash = hash;
    }

    public byte[] getSalt() {
        return salt;
    }

    public byte[] getHash() {
        return hash;
    }

    public boolean verifyPassword(String plainTextPassword){
        return Arrays.equals(
                this.hash,
                PasswordUtils.generateHash(plainTextPassword, this.salt)
        );
    }
}
