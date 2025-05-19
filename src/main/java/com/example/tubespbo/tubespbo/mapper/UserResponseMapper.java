package com.example.tubespbo.tubespbo.mapper;

import com.example.tubespbo.tubespbo.entity.UserEntity;
import com.example.tubespbo.tubespbo.model.UserResponse;

public class UserResponseMapper {
    private static UserResponse ToUserResponseMapper(UserEntity user) {
        return UserResponse.builder().username(user.getUsername()).email(user.getEmail()).build();
    }
}

