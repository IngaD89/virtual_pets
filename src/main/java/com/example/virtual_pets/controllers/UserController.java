package com.example.virtual_pets.controllers;

import com.example.virtual_pets.dto.UserDto;
import com.example.virtual_pets.models.Password;
import com.example.virtual_pets.models.User;
import com.example.virtual_pets.services.UserService;
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

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        User createdUser = userService.createUser(
                userDto.getEmail(),
                userDto.getNickname(),
                userDto.getPassword()
        );
        return ResponseEntity.ok(createdUser);
    }
}
