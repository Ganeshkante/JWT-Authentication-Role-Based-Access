package com.example.jwt_mini.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jwt_mini.model.User;
import com.example.jwt_mini.repository.UserRepository;
import com.example.jwt_mini.security.JwtUtil;

@Service
public class AuthService {
    @Autowired private UserRepository repo;
    @Autowired private PasswordEncoder encoder;
    @Autowired private JwtUtil jwtUtil;

    public String register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return "User registered successfully!";
    }

    public String login(String username, String password) {
        User user = repo.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        if (encoder.matches(password, user.getPassword())) {
            return jwtUtil.generateToken(user.getUsername(), user.getRole());
        } else {
            throw new RuntimeException("Invalid password");
        }
    }
}

