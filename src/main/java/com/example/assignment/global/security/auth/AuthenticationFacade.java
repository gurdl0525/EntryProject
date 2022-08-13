package com.example.assignment.global.security.auth;

import com.example.assignment.domain.entity.user.User;
import com.example.assignment.domain.entity.user.UserRepository;
import com.example.assignment.global.error.exception.InvalidTokenException;
import com.example.assignment.global.error.exception.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationFacade {

    private final UserRepository userRepository;

    public User getCurrentUser(){
        try{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthDetails authDetails = (AuthDetails) authentication.getPrincipal();
        return userRepository.findByAccountId(authDetails.getUsername())
                .orElseThrow(NotFoundUserException::getInstance);
        } catch (Exception e){
            throw InvalidTokenException.getInstance();
        }
    }

}
