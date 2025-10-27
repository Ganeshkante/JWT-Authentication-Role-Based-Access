package com.example.jwt_mini.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jwt_mini.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
