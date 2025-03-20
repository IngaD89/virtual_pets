package com.example.virtual_pets.controllers;

import com.example.virtual_pets.dto.UserRequest;
import com.example.virtual_pets.models.User;
import com.example.virtual_pets.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth/register")
    @SecurityRequirement(name = "")
    @Operation(summary = "Crear usuario", description = "Crea un nuevo usuario en el sistema.")
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
        User createdUser = userService.createUser(
                userRequest.getEmail(),
                userRequest.getNickname(),
                userRequest.getPassword()
        );
        return ResponseEntity.ok(createdUser);

    }
}
