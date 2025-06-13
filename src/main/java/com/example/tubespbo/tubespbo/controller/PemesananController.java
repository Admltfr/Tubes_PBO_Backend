package com.example.tubespbo.tubespbo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tubespbo.tubespbo.exception.ApiException;
import com.example.tubespbo.tubespbo.mapper.ApiResponseMapper;
import com.example.tubespbo.tubespbo.model.request.PemesananRequest;
import com.example.tubespbo.tubespbo.model.request.PemesananUpdateRequest;
import com.example.tubespbo.tubespbo.model.response.ApiResponse;
import com.example.tubespbo.tubespbo.model.response.PemesananResponse;
import com.example.tubespbo.tubespbo.service.PemesananService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pemesanan")
@RequiredArgsConstructor
public class PemesananController {

    @Autowired
    private PemesananService pemesananService;

    @Autowired
    private ApiResponseMapper responseBuilder;

    @GetMapping("/riwayat/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<PemesananResponse>>> getPemesananByPenumpangId(@PathVariable Long id) {
        try {
            List<PemesananResponse> pemesananList = pemesananService.getPemesananByPenumpangId(id);
            ApiResponse<List<PemesananResponse>> response = responseBuilder.ToApiResponse(HttpStatus.OK, "Berhasil mengambil data pemesanan", pemesananList);
            return ResponseEntity.ok(response);
        } catch (ApiException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiException("Gagal mengambil data pemesanan untuk penumpang dengan ID " + id);
        }
    }



    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<PemesananResponse>>> getAllPemesanan() {
        try {
            List<PemesananResponse> schedules = pemesananService.getAllPemesanan();
            ApiResponse<List<PemesananResponse>> response = responseBuilder.ToApiResponse(HttpStatus.OK, "Berhasil mengambil data semua pemesanan", schedules);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            throw new ApiException("Gagal mengambil data semua pemesanan");
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

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<PemesananResponse>> createPemesanan(@RequestBody @Valid PemesananRequest request) {
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

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse<PemesananResponse>> updatePemesanan(@PathVariable Long id, @RequestBody @Valid PemesananUpdateRequest request) {
        try {
            PemesananResponse result = pemesananService.updatePemesanan(request);
            ApiResponse<PemesananResponse> response = responseBuilder.ToApiResponse(HttpStatus.OK, "Berhasil mengupdate data pemesanan", result);
            return ResponseEntity.ok(response);
        } catch (ApiException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiException("Terjadi kesalahan saat mengubah data kereta");
        }
    }

    @DeleteMapping("/delete/{id}")
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
