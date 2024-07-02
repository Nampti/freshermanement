package com.example.freshermanement.service;

import java.util.Collections;

import org.springframework.stereotype.Service;
import com.example.freshermanement.entity.User;
import com.example.freshermanement.enums.Role;
import com.example.freshermanement.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor injection of the UserRepository
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUserWithRole(String username, String email, String password, Role role) {
        // Create a new User object
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password); 
        newUser.setRoles(Collections.singleton(role));
        return newUser;
    }
}