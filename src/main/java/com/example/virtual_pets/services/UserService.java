package com.example.virtual_pets.services;

import com.example.virtual_pets.exceptions.EmailAlreadyInUseException;
import com.example.virtual_pets.exceptions.NicknameAlreadyInUseException;
import com.example.virtual_pets.exceptions.UserNotFoundException;
import com.example.virtual_pets.models.Password;
import com.example.virtual_pets.models.User;
import com.example.virtual_pets.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void assertEmailInUse(String email){
        this.userRepository
                .findAll()
                .stream()
                .filter(user -> email.equals(user.getEmail()))
                .findFirst()
                .orElseThrow(() -> new EmailAlreadyInUseException("Email: " + email + " already in use"));
    }

    private void assertNicknameInUse(String nickname){
        this.userRepository
                .findAll()
                .stream()
                .filter(user -> nickname.equals(user.getNickname()))
                .findFirst()
                .orElseThrow(() -> new NicknameAlreadyInUseException("Nickname: " + nickname + " already in use"));
    }

    public User createUser(
            String email,
            String nickname,
            String plainTextPassword
    ){
        this.assertEmailInUse(email);
        this.assertNicknameInUse(nickname);

        Password encodedPassword = new Password(plainTextPassword);

        User user = new User(email, nickname, encodedPassword);

        return userRepository.save(user);
    }

    public User findUserById(UUID userID){
        return this
                .userRepository
                .findById(userID)
                .orElseThrow(UserNotFoundException::new);
    }

}
