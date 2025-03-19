package com.example.virtual_pets.controllers;

import com.example.virtual_pets.common.utils.JwtUtils;
import com.example.virtual_pets.dto.LoginRequest;
import com.example.virtual_pets.dto.LoginResponse;
import com.example.virtual_pets.models.Session;
import com.example.virtual_pets.models.User;
import com.example.virtual_pets.services.SessionService;
import com.example.virtual_pets.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticación", description = "Operaciones de autenticación y manejo de usuarios.")
public class SessionController {

    private final SessionService sessionService;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    public SessionController(
            SessionService sessionService,
            UserService userService,
            JwtUtils jwtUtils
    ) {
        this.sessionService = sessionService;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }


    @PostMapping("/login")
    @Operation(summary = "Autenticación de usuario", description = "Inicia sesión y devuelve el token JWT.")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest loginRequest
    ){
        Session session = sessionService.login(
                loginRequest.getNickname(),
                loginRequest.getPassword()
        );

        User user = userService.findUserById(session.getUserId());

        String token = jwtUtils.generateToken(session.getUserId(), user.getRole());

        return ResponseEntity.ok(new LoginResponse(session.getId(), token));
    }
}
