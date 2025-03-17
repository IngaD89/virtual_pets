package com.example.virtual_pets.services;

import com.example.virtual_pets.exceptions.IncorrectCredentialsException;
import com.example.virtual_pets.models.Session;
import com.example.virtual_pets.models.User;
import com.example.virtual_pets.repositories.SessionRepository;
import com.example.virtual_pets.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    public SessionService(
            SessionRepository sessionRepository,
            UserRepository userRepository
    ) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    public User checkCredentials(String nickname, String password) {
        return this.userRepository
                .findByNickname(nickname)
                //.filter(user -> user.getPassword().verifyPassword(password))
                .map(user -> {
                    System.out.println("Usuario encontrado: " + user.getNickname());
                    boolean isValid = user.getPassword().verifyPassword(password);
                    System.out.println("¿Contraseña válida?: " + isValid);
                    return isValid ? user : null;
                })
                //.orElseThrow(IncorrectCredentialsException::new);
                .orElseThrow(() -> {
                    System.out.println("Credenciales incorrectas para nickname: " + nickname + " " + password);
                    return new IncorrectCredentialsException();
                });
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
}
