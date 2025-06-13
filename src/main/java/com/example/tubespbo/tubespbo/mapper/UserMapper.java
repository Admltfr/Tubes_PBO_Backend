package com.example.tubespbo.tubespbo.mapper;

import org.springframework.stereotype.Component;

import com.example.tubespbo.tubespbo.entity.PenumpangEntity;
import com.example.tubespbo.tubespbo.entity.UserEntity;
import com.example.tubespbo.tubespbo.model.response.UserResponse;
import com.example.tubespbo.tubespbo.model.response.PenumpangResponse;

@Component
public class UserMapper {
    public UserResponse ToUserResponseMapper(UserEntity user) {
        String nomorTelepon = null;
        if (user instanceof PenumpangEntity) {
            nomorTelepon = ((PenumpangEntity) user).getNomorTelepon();
        }
        return UserResponse.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .nomorTelepon(nomorTelepon)
                    .build();
    }
}

