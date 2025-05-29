package com.example.tubespbo.tubespbo.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.tubespbo.tubespbo.entity.AdminEntity;
import com.example.tubespbo.tubespbo.entity.PenumpangEntity;
import com.example.tubespbo.tubespbo.model.request.RegisterRequest;
import com.example.tubespbo.tubespbo.model.response.RegisterResponse;

@Component
public class AuthMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public PenumpangEntity ToPenumpangEntity(RegisterRequest request) {
        return PenumpangEntity.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .email(request.getEmail())
                    .nomorTelepon(request.getNomorTelepon())
                    .build();
    }

    public AdminEntity ToAdminEntity(RegisterRequest request) {
        return AdminEntity.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .email(request.getEmail())
                    .build();
    }

    public RegisterResponse ToRegisterResponse(RegisterRequest request) {
        return RegisterResponse.builder()
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .build();
    }
}
