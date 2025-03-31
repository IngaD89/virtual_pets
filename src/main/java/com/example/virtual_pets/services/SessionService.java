package com.example.virtual_pets.services;

import com.example.virtual_pets.common.utils.JwtUtils;
import com.example.virtual_pets.exceptions.authExceptios.AuthenticationException;
import com.example.virtual_pets.exceptions.authExceptios.IncorrectCredentialsException;
import com.example.virtual_pets.exceptions.authExceptios.SessionNotFoundException;
import com.example.virtual_pets.models.Session;
import com.example.virtual_pets.models.User;
import com.example.virtual_pets.models.enums.Role;
import com.example.virtual_pets.repositories.SessionRepository;
import com.example.virtual_pets.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final JwtUtils jwtUtils;

    public SessionService(
            SessionRepository sessionRepository,
            UserRepository userRepository,
            AuthenticationService authenticationService,
            JwtUtils jwtUtils
    ) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
        this.jwtUtils = jwtUtils;
    }

    public User checkCredentials(String nickname, String password) {
        return this.userRepository
                .findByNickname(nickname)
                .filter(user -> user.getPassword().verifyPassword(password))
                .orElseThrow(IncorrectCredentialsException::new);
    }

    public Session login(
            String nickname,
            String password
    ) {
        User user = this.checkCredentials(nickname, password);
        return this
                .sessionRepository
                .findByUserId(user.getId())
                .filter(session -> session.getExpiredAt().isAfter(Instant.now()))
                .orElseGet(() -> {
                    Session session = new Session(user.getId());
                    return this
                            .sessionRepository
                            .save(session);
                });
    }

    public void logout() {
        UUID authenticatedUserId = authenticationService.getAuthenticatedUserId();

        Session session = sessionRepository.findByUserId(authenticatedUserId)
                .filter(s -> s.getExpiredAt().isAfter(Instant.now()))
                .orElseThrow(() -> new SessionNotFoundException("No active session was found"));

        sessionRepository.deleteById(session.getId());
    }

    public Map<String, String> refreshSession() {
        Map<String, String> refreshResponse = new HashMap<>();
        UUID authenticatedUserId = authenticationService.getAuthenticatedUserId();

        Session session = sessionRepository.findByUserId(authenticatedUserId)
                .orElseThrow(() -> new SessionNotFoundException("Session not found, please log in"));

        if (session.getExpiredAt().isBefore(Instant.now())) {
            throw new AuthenticationException("Session expired, please log in again");
        }

        session.setExpiredAt(Instant.now().plus(1, ChronoUnit.HOURS));
        session.setUpdatedAt(Instant.now());

        sessionRepository.save(session);

        String userRole = authenticationService.getAuthenticatedUserRole();

        userRole = userRole.replace("ROLE_", "");

        String token = jwtUtils.generateToken(authenticatedUserId, Role.valueOf(userRole));

        refreshResponse.put("id", String.valueOf(session.getId()));
        refreshResponse.put("token", token);

        return refreshResponse;
    }


}
