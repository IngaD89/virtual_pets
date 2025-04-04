package com.example.virtual_pets.services;

import com.example.virtual_pets.exceptions.authExceptios.AuthenticationException;
import com.example.virtual_pets.exceptions.userExceptions.UserRoleNotMatchException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthenticationService {

    public UUID getAuthenticatedUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try{
             return UUID.fromString(authentication.getName());
        }catch (IllegalArgumentException e){
            throw new AuthenticationException("Incorrect user id in token");
        }
    }

    public String getAuthenticatedUserRole(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElseThrow(UserRoleNotMatchException::new);
    }


    public boolean isAdmin(String role) {
        return "USER_ADMIN".equalsIgnoreCase(role);
    }


}
