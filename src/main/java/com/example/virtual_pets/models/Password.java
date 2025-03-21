package com.example.virtual_pets.models;


import com.example.virtual_pets.common.utils.PasswordUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Arrays;

@Embeddable
public class Password {

    @Column(name = "password_salt", length = 64)
    private String salt;

    @Column(name = "password_hash", length = 128)
    private String hash;

    public Password() {
    }

    public Password(String salt, String hash) {
        this.salt = salt;
        this.hash = hash;
    }

    public Password(String plainTextPassword) {
        byte[] saltBytes = PasswordUtils.generateSalt();
        byte[] hashBytes = PasswordUtils.generateHash(plainTextPassword, saltBytes);

        this.salt = bytesToHex(saltBytes);
        this.hash = bytesToHex(hashBytes);
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public String getHash() {
        return hash;
    }

    public boolean verifyPassword(String plainTextPassword){
        byte[] saltBytes = hexToBytes(this.salt);
        byte[] expectedHash = PasswordUtils.generateHash(plainTextPassword, saltBytes);

        return Arrays.equals(expectedHash, hexToBytes(this.hash));
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    private static byte[] hexToBytes(String hex) {
        int length = hex.length();
        byte[] data = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }
}



