package com.example.tubespbo.tubespbo.mapper;

import org.springframework.stereotype.Component;

import com.example.tubespbo.tubespbo.entity.PenumpangEntity;
import com.example.tubespbo.tubespbo.entity.UserEntity;
import com.example.tubespbo.tubespbo.model.response.UserResponse;

@Component
public class UserMapper {
    public UserResponse ToUserResponseMapper(UserEntity user) {
        String nomorTelepon = null;
        if (user instanceof PenumpangEntity penumpangEntity) {
            nomorTelepon = penumpangEntity.getNomorTelepon();
        }
        return UserResponse.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .nomorTelepon(nomorTelepon)
                    .build();
    }
}

