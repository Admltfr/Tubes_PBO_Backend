package com.example.tubespbo.tubespbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tubespbo.tubespbo.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);
}
