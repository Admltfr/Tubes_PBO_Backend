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
import com.example.tubespbo.tubespbo.model.request.JadwalRequest;
import com.example.tubespbo.tubespbo.model.response.ApiResponse;
import com.example.tubespbo.tubespbo.model.response.JadwalResponse;
import com.example.tubespbo.tubespbo.service.JadwalService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class JadwalController {

    @Autowired
    private JadwalService jadwalService;

    @Autowired
    private ApiResponseMapper responseBuilder;


    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<List<JadwalResponse>>> getAllJadwal() {
        try {
            List<JadwalResponse> jadwals = jadwalService.getAll();
            ApiResponse<List<JadwalResponse>> response = responseBuilder.ToApiResponse(HttpStatus.OK, "Berhasil mengambil data jadwal", jadwals);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (ApiException ex) {
            throw ex;
        }catch (Exception ex) {
            throw new ApiException("Terjadi kesalahan dalam mengambil data jadwal");
        }
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse<JadwalResponse>> addJadwal(@RequestBody @Valid JadwalRequest req) {
        try {
            JadwalResponse saved = jadwalService.add(req);
            ApiResponse<JadwalResponse> response = responseBuilder.ToApiResponse(HttpStatus.CREATED, "Berhasil membuat data jadwal", saved);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (ApiException ex) {
            throw ex;
        }catch (Exception ex) {
            throw new ApiException("Terjadi kesalahan dalam menambah jadwal");
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteJadwal(@PathVariable Long id) {
        try {
            jadwalService.deleteJadwal(id);
            ApiResponse<Void> response =  responseBuilder.ToApiResponse(HttpStatus.OK, "Berhasil menghapus jadwal dengan ID: "+ id, null );
            return ResponseEntity.ok(response);
        }catch (ApiException ex) {
            throw ex;
        }catch (Exception ex) {
            throw new ApiException("Terjadi kesalahan dalam menghapus jadwal");
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse<JadwalResponse>> updateJadwal(@PathVariable Long id, @RequestBody @Valid JadwalRequest req) {
        try {
            JadwalResponse updated = jadwalService.updateJadwal(id, req);
            ApiResponse<JadwalResponse> response = responseBuilder.ToApiResponse(HttpStatus.OK,  "Jadwal berhasil diupdate", updated);
            return ResponseEntity.ok(response);
        }catch (ApiException ex) {
            throw ex;
        }catch (Exception ex) {
            throw new ApiException("Terjadi kesalahan dalam mengupdate jadwal");
        }
    }
}