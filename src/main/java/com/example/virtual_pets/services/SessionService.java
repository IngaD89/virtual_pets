package com.example.virtual_pets.services;

import com.example.virtual_pets.repositories.SessionRepository;

public class SessionService {
    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }


}
