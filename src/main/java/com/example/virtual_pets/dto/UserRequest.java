package com.example.virtual_pets.dto;

import com.example.virtual_pets.models.Password;
import com.example.virtual_pets.models.User;

public class UserRequest {
    private String email;
    private String nickname;
    private String password;

    public UserRequest() {
    }

    public UserRequest(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User toUser() {
        return new User(email, nickname, new Password(password));
    }
}
