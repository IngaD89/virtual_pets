package com.example.virtual_pets.common;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        byte[] salt = PasswordUtils.generateSalt();
        byte[] hash = PasswordUtils.generateHash(rawPassword.toString(), salt);
        // Codificar el hash y el salt en un formato adecuado (por ejemplo, Base64 o Hexadecimal)
        return Arrays.toString(hash) + ":" + Arrays.toString(salt); // Este es solo un ejemplo, ajusta según lo que necesites.
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        // Dividir el hash y salt almacenados en la base de datos
        String[] parts = encodedPassword.split(":");
        byte[] hash = Arrays.copyOf(parts[0].getBytes(), parts[0].length()); // Ajusta la lógica
        byte[] salt = Arrays.copyOf(parts[1].getBytes(), parts[1].length()); // Ajusta la lógica

        byte[] generatedHash = PasswordUtils.generateHash(rawPassword.toString(), salt);

        return Arrays.equals(generatedHash, hash);
    }
}


