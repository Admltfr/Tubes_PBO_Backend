package com.example.tubespbo.tubespbo.mapper;

import org.springframework.stereotype.Component;

import com.example.tubespbo.tubespbo.entity.UserEntity;
import com.example.tubespbo.tubespbo.model.response.UserResponse;

@Component
public class UserMapper {
    public UserResponse ToUserResponseMapper(UserEntity user) {
        return UserResponse.builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .build();
    }
}

