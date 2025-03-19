package com.example.virtual_pets.common.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class PasswordUtils {

    private static final Logger LOGGER = LogManager.getLogger(PasswordUtils.class);


    private static final String HASH_ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final String SALT_ALGORITHM = "NativePRNG";
    private static final int ITERATIONS = 100000;
    private static final int SALT_LENGTH = 16;
    private static final int KEY_LENGTH = SALT_LENGTH * 8;
    private static SecureRandom SECURE_RANDOM;


    public static SecureRandom newInstance() {
        if (SECURE_RANDOM == null) {
            try {
                PasswordUtils.SECURE_RANDOM = SecureRandom.getInstance(SALT_ALGORITHM);
            } catch (NoSuchAlgorithmException e) {
                LOGGER.warn("Algorithm 'NativePRNG' not found. Falling back to default SecureRandom: {}", e.getMessage());
                PasswordUtils.SECURE_RANDOM = new SecureRandom();
            }
        }
        return SECURE_RANDOM;
    }

    public static byte[] generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];

        PasswordUtils.newInstance().nextBytes(salt);

        return salt;
    }

    public static byte[] generateHash(
            String plainTextPassword,
            byte[] salt
    ) {
        try {
            PBEKeySpec pbeKeySpec = new PBEKeySpec(
                    plainTextPassword.toCharArray(),
                    salt,
                    ITERATIONS,
                    KEY_LENGTH
            );

            return SecretKeyFactory
                    .getInstance(HASH_ALGORITHM)
                    .generateSecret(pbeKeySpec)
                    .getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            LOGGER.info(e.getMessage());
            throw new RuntimeException("Error has been occurred while generating hash");
        }
    }
}
