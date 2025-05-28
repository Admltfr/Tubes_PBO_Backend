package com.example.tubespbo.tubespbo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tubespbo.tubespbo.entity.UserEntity;
import com.example.tubespbo.tubespbo.mapper.UserMapper;
import com.example.tubespbo.tubespbo.model.response.UserResponse;
import com.example.tubespbo.tubespbo.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::ToUserResponseMapper)
                .collect(Collectors.toList());
    }

    public UserResponse getUserByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) return null;
        return UserMapper.ToUserResponseMapper(user);
    }
}
