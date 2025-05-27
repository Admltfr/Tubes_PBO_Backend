package com.example.tubespbo.tubespbo.mapper;

import com.example.tubespbo.tubespbo.entity.UserEntity;
import com.example.tubespbo.tubespbo.model.response.UserResponse;

public class UserResponseMapper {
    public static UserResponse ToUserResponseMapper(UserEntity user) {
        return UserResponse.builder().username(user.getUsername()).email(user.getEmail()).build();
    }
}

