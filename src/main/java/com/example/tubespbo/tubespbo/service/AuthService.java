package com.example.tubespbo.tubespbo.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.tubespbo.tubespbo.entity.AdminEntity;
import com.example.tubespbo.tubespbo.entity.PenumpangEntity;
import com.example.tubespbo.tubespbo.entity.UserEntity;
import com.example.tubespbo.tubespbo.model.request.LoginRequest;
import com.example.tubespbo.tubespbo.model.request.RegisterRequest;
import com.example.tubespbo.tubespbo.repository.UserRepository;
import com.example.tubespbo.tubespbo.security.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String register(RegisterRequest request) {
        if ("PENUMPANG".equalsIgnoreCase(request.getRole())) {
            PenumpangEntity penumpang = PenumpangEntity.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .email(request.getEmail())
                    .nomorTelepon(request.getNomorTelepon())
                    .build();
            userRepository.save(penumpang);
            return "Register penumpang sukses";
        } else if ("ADMIN".equalsIgnoreCase(request.getRole())) {
            AdminEntity admin = AdminEntity.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .email(request.getEmail())
                    .build();
            userRepository.save(admin);
            return "Register admin sukses";
        }
        return "Role tidak valid";
    }

    public String login(LoginRequest request) {
        UserEntity user = userRepository.findByUsername(request.getUsername());
        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String role = user instanceof PenumpangEntity ? "PENUMPANG" : "ADMIN";
            return jwtUtil.generateToken(user.getUsername(), role);
        }
        return "Username atau password salah";
    }

    public String logout() {
        return "Logout sukses, silakan hapus token di client.";
    }

    public String forgotPassword(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) return "Email tidak ditemukan";
        String resetToken = UUID.randomUUID().toString();
        user.setPassword(resetToken); 
        userRepository.save(user);
        return "Kode reset password: " + resetToken;
    }

    public String resetPassword(String email, String resetToken, String newPassword) {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null || !user.getPassword().equals(resetToken)) {
            return "Token reset tidak valid";
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return "Password berhasil direset";
    }
}
