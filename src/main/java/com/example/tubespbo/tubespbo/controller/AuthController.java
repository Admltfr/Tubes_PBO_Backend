package com.example.tubespbo.tubespbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tubespbo.tubespbo.exception.ApiException;
import com.example.tubespbo.tubespbo.exception.AuthException;
import com.example.tubespbo.tubespbo.mapper.ApiResponseMapper;
import com.example.tubespbo.tubespbo.model.request.LoginRequest;
import com.example.tubespbo.tubespbo.model.request.RegisterRequest;
import com.example.tubespbo.tubespbo.model.request.ResetPasswordRequest;
import com.example.tubespbo.tubespbo.model.response.ApiResponse;
import com.example.tubespbo.tubespbo.model.response.RegisterResponse;
import com.example.tubespbo.tubespbo.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ApiResponseMapper responseBuilder;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponse>> register(@RequestBody @Valid RegisterRequest request) {
        try {
            RegisterResponse result = authService.register(request);
            ApiResponse<RegisterResponse> response = responseBuilder.ToApiResponse(HttpStatus.OK, "Registrasi sukses", result);
            return ResponseEntity.ok(response);
        } catch (ApiException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiException("Terjadi kesalahan saat register");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody @Valid LoginRequest request) {
        String token = authService.login(request);
        try {
            if (token == null) {
                throw new AuthException("Username atau password salah");
            }
            ApiResponse<String> response = responseBuilder.ToApiResponse(HttpStatus.OK, "Login sukses", token);
            return ResponseEntity.ok(response);
        } catch (AuthException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiException("Terjadi kesalahan saat login");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout() {
        try {
            String result = authService.logout();
            ApiResponse<String> response = responseBuilder.ToApiResponse(HttpStatus.OK, "Logout sukses", result);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            throw new ApiException("Terjadi kesalahan saat logout");
        }
    }

    @PostMapping("/reset-password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<String>> resetPassword(@RequestBody @Valid ResetPasswordRequest request) {
        try {
            String result = authService.resetPassword(request.getEmail(), request.getNewPassword());
            ApiResponse<String> response = responseBuilder.ToApiResponse(HttpStatus.OK, "Reset password berhasil", result);
            return ResponseEntity.ok(response);
        } catch (ApiException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiException("Terjadi kesalahan saat reset password");
        }
    }
}
