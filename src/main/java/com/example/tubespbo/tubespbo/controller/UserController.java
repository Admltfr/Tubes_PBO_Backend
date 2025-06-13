package com.example.tubespbo.tubespbo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tubespbo.tubespbo.exception.ApiException;
import com.example.tubespbo.tubespbo.mapper.ApiResponseMapper;
import com.example.tubespbo.tubespbo.model.response.ApiResponse;
import com.example.tubespbo.tubespbo.model.response.UserResponse;
import com.example.tubespbo.tubespbo.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApiResponseMapper responseBuilder;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        try {
            List<UserResponse> users = userService.getAllUsers();
            ApiResponse<List<UserResponse>> response = responseBuilder.ToApiResponse(HttpStatus.OK, "Berhasil mengambil data semua user", users);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            throw new ApiException("Gagal mengambil data semua user");
        }
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<UserResponse>> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            UserResponse user = userService.getUserByUsername(userDetails.getUsername());
            ApiResponse<UserResponse> response = responseBuilder.ToApiResponse(HttpStatus.OK, "Berhasil mengambil data user", user);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            throw new ApiException("Gagal mengambil data user");
        }
    }

    
}