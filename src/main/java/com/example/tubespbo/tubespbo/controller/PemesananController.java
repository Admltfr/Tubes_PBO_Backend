package com.example.tubespbo.tubespbo.controller;

import com.example.tubespbo.tubespbo.exception.ApiException;
import com.example.tubespbo.tubespbo.mapper.ApiResponseMapper;
import com.example.tubespbo.tubespbo.model.request.PemesananRequest;
import com.example.tubespbo.tubespbo.model.response.ApiResponse;
import com.example.tubespbo.tubespbo.model.response.PemesananResponse;
import com.example.tubespbo.tubespbo.service.PemesananService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pemesanan")
@RequiredArgsConstructor
public class PemesananController {

    private final PemesananService pemesananService;

    @Autowired
    private ApiResponseMapper responseBuilder;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<PemesananResponse>> createPemesanan(@RequestBody PemesananRequest request) {
        try {
            PemesananResponse result = pemesananService.createPemesanan(request);
            ApiResponse<PemesananResponse> response = responseBuilder.ToApiResponse(HttpStatus.CREATED, "Pemesanan berhasil dibuat", result);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (ApiException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiException("Terjadi kesalahan dalam membuat pemesanan");
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<PemesananResponse>> getPemesananById(@PathVariable Long id) {
        try {
            PemesananResponse responseData = pemesananService.getPemesananById(id);
            ApiResponse<PemesananResponse> response = responseBuilder.ToApiResponse(HttpStatus.OK, "Berhasil mengambil data pemesanan", responseData);
            return ResponseEntity.ok(response);
        } catch (ApiException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiException("Pemesanan dengan ID " + id + " tidak ditemukan");
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse<String>> deletePemesanan(@PathVariable Long id) {
        try {
            pemesananService.deletePemesanan(id);
            ApiResponse<String> response = responseBuilder.ToApiResponse(HttpStatus.OK, "Pemesanan berhasil dihapus", "ID: " + id);
            return ResponseEntity.ok(response);
        } catch (ApiException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiException("Gagal menghapus pemesanan dengan ID " + id);
        }
    }
}
