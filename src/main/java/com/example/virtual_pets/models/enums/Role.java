package com.example.virtual_pets.models.enums;

import java.util.List;

public enum Role {
    USER_BASIC,
    USER_ADMIN;

    public static List<Role> getAllRoles() {
        return List.of(values());
    }
}
