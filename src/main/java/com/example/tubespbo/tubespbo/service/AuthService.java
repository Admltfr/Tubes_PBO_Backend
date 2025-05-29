package com.example.tubespbo.tubespbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.tubespbo.tubespbo.entity.AdminEntity;
import com.example.tubespbo.tubespbo.entity.PenumpangEntity;
import com.example.tubespbo.tubespbo.entity.UserEntity;
import com.example.tubespbo.tubespbo.mapper.AuthMapper;
import com.example.tubespbo.tubespbo.model.request.LoginRequest;
import com.example.tubespbo.tubespbo.model.request.RegisterRequest;
import com.example.tubespbo.tubespbo.model.response.RegisterResponse;
import com.example.tubespbo.tubespbo.repository.UserRepository;
import com.example.tubespbo.tubespbo.security.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthMapper registrasiMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public RegisterResponse register(RegisterRequest request) {
        //ini maksudnya, penumpang atau admin itu bakal diubah dari registerreq ke entity
        if ("PENUMPANG".equalsIgnoreCase(request.getRole())) {
            PenumpangEntity user = registrasiMapper.ToPenumpangEntity(request);
            userRepository.save(user);
        } else if ("ADMIN".equalsIgnoreCase(request.getRole())) {
            AdminEntity user = registrasiMapper.ToAdminEntity(request);
            userRepository.save(user);
        }
        return registrasiMapper.ToRegisterResponse(request);
    }

    public String login(LoginRequest request) {
        UserEntity user = userRepository.findByUsername(request.getUsername());
        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String role = user instanceof PenumpangEntity ? "PENUMPANG" : "ADMIN";
            return jwtUtil.generateToken(user.getUsername(), role);
        }
        return null;
    }

    public String logout() {
        return null;
    }

    public String resetPassword(String email, String newPassword) {
        UserEntity user = userRepository.findByEmail(email);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return "Password berhasil direset";
    }
}
