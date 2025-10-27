package com.example.jwt_mini.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.jwt_mini.model.User;
import com.example.jwt_mini.service.AuthService;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired private AuthService service;

    @PostMapping("/register")  
    public String register(@RequestBody User user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
        String token = service.login(user.getUsername(), user.getPassword());
        return Map.of("token", token);
    }
}

@RestController
@RequestMapping("/user")
class UserController {
    @GetMapping("/welcome")
    public String userWelcome() {
        return "Welcome, USER!";
    }
}

@RestController
@RequestMapping("/admin")
class AdminController {
    @GetMapping("/welcome")
    public String adminWelcome() {
        return "Welcome, ADMIN!";
    }
}
