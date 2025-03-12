package com.example.virtual_pets.common;

import com.example.virtual_pets.models.enums.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class JwtUtils {

    private static final String SECRET_KEY = "TuClaveSecretaSuperSeguraDebeSerMasLarga";
    private static final long EXPIRATION_TIME = 86400000; // 24 horas

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(UUID userID, Role role) {
        return Jwts.builder()
                .claims(Map.of(
                        "sub", userID,
                        "role", role.name(),
                        "iat", new Date(),
                        "exp", new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                )
                .signWith(getSigningKey())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts
                    .parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public Role extractRole(String token) {
        Claims claims = extractClaims(token);
        return Role.valueOf(claims.get("role", String.class));
    }


    public Claims extractClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}

