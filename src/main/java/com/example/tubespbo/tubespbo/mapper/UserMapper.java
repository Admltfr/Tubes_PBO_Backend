package com.example.tubespbo.tubespbo.mapper;

import org.springframework.stereotype.Component;

import com.example.tubespbo.tubespbo.entity.PenumpangEntity;
import com.example.tubespbo.tubespbo.entity.UserEntity;
import com.example.tubespbo.tubespbo.model.response.UserResponse;
import com.example.tubespbo.tubespbo.model.response.PenumpangResponse;

@Component
public class UserMapper {
    public UserResponse ToUserResponseMapper(UserEntity user) {
        return UserResponse.builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .build();
    }

    public PenumpangResponse ToPenumpangResponseMapper(PenumpangEntity penumpang) {
        return PenumpangResponse.builder()
                    .username(penumpang.getUsername())
                    .email(penumpang.getEmail())
                    .nomorTelepon(penumpang.getNomorTelepon())
                    .build();
    }
}

