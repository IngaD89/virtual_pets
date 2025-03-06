package com.example.virtual_pets.models;


import com.example.virtual_pets.common.PasswordUtils;
import java.util.Arrays;

public class Password {

    private final byte[] salt;
    private final byte[] hash;

    public Password(byte[] salt, byte[] hash) {
        this.salt = salt;
        this.hash = hash;
    }

    public Password(String plainTextPassword) {
        this.salt = PasswordUtils.generateSalt();
        this.hash = PasswordUtils.generateHash(plainTextPassword, this.salt);
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
