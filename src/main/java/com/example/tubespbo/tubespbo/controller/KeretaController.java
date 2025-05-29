package com.example.tubespbo.tubespbo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tubespbo.tubespbo.exception.ApiException;

import com.example.tubespbo.tubespbo.model.response.ApiResponse;
import com.example.tubespbo.tubespbo.model.response.KeretaResponse;
import com.example.tubespbo.tubespbo.mapper.ApiResponseMapper;
import com.example.tubespbo.tubespbo.service.KeretaService;

@RestController
@RequestMapping("/api/trains")
public class KeretaController {

    @Autowired
    private KeretaService keretaService;

    @Autowired
    private ApiResponseMapper responseBuilder;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse<List<KeretaResponse>>> getAllUsers() {
        try {
            List<KeretaResponse> trains = keretaService.getAllKereta();
            ApiResponse<List<KeretaResponse>> response = responseBuilder.ToApiResponse(HttpStatus.OK, "Berhasil mengambil data semua kereta", trains);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            throw new ApiException("Gagal mengambil data semua user");
        }
    }

    // @GetMapping("/id")
    // @PreAuthorize("isAuthenticated()")
    // public KeretaResponse getCurrentKereta(String asal) {
    //     return keretaService.getKeretaByAsal(asal);
    // }
}
