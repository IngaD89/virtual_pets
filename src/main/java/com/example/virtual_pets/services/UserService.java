package com.example.virtual_pets.services;

import com.example.virtual_pets.exceptions.userExceptions.EmailAlreadyInUseException;
import com.example.virtual_pets.exceptions.userExceptions.NicknameAlreadyInUseException;
import com.example.virtual_pets.exceptions.userExceptions.UserNotFoundException;
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
        if(this.userRepository.existsByEmail(email)){
            throw new EmailAlreadyInUseException("Email " + email + " already in use");
        }
    }

    private void assertNicknameInUse(String nickname){
        if(this.userRepository.existsByEmail(nickname)){
            throw new NicknameAlreadyInUseException("Nickname: " + nickname + " already in use");
        }
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
