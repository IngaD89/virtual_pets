package com.example.virtual_pets.common.utils;

import com.example.virtual_pets.models.enums.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class JwtUtils {

    private static final Logger LOGGER = LogManager.getLogger(JwtUtils.class);


    private static final String SECRET_KEY = "TuClaveSecretaSuperSeguraDebeSerMasLarga";
    private static final long EXPIRATION_TIME = 86400000;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(UUID userID, Role role) {
        LOGGER.info("Generating token for user with ID: {}", userID.toString());
        try {
            String token = Jwts.builder()
                    .claims(Map.of(
                            "sub", userID.toString(),
                            "role", role.name(),
                            "iat", new Date(),
                            "exp", new Date(System.currentTimeMillis() + EXPIRATION_TIME * 1000)
                    ))
                    .signWith(getSigningKey())
                    .compact();
            LOGGER.info("Token successfully generated for user with ID: {}", userID.toString());
            return token;
        } catch (Exception e) {
            LOGGER.error("Error generating token for user with ID: {}", userID.toString(), e);
            throw e;
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts
                    .parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            LOGGER.info("Token is valid");
            return true;
        } catch (JwtException e) {
            LOGGER.info("Invalid token: {}", token, e);
            return false;
        }
    }

    public Role extractRole(String token) {
        Claims claims = extractClaims(token);
        Role role = Role.valueOf(claims.get("role", String.class));
        LOGGER.info("Extracted role: {}", role);
        return role;
    }


    public Claims extractClaims(String token) {
       try{
           Claims claims = Jwts
                   .parser()
                   .verifyWith(getSigningKey())
                   .build()
                   .parseSignedClaims(token)
                   .getPayload();
           LOGGER.info("Claims successfully extracted");
           return claims;
       }catch (JwtException e){
           LOGGER.error("Error extracting claims from token: {}", token, e);
           throw new JwtException("Error extracting claims", e);
       }
    }

    public String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        LOGGER.warn("No token was founded");
        return null;
    }
}

