package com.example.tubespbo.tubespbo.controller;

import com.example.tubespbo.tubespbo.entity.Jadwal;
import com.example.tubespbo.tubespbo.entity.PemesananEntity;
import com.example.tubespbo.tubespbo.entity.PenumpangEntity;
import com.example.tubespbo.tubespbo.entity.UserEntity;
import com.example.tubespbo.tubespbo.mapper.ApiResponseMapper;
import com.example.tubespbo.tubespbo.mapper.PemesananResponseMapper;
import com.example.tubespbo.tubespbo.model.response.PemesananResponse;
import com.example.tubespbo.tubespbo.repository.JadwalRepository;
import com.example.tubespbo.tubespbo.repository.PemesananRepository;
import com.example.tubespbo.tubespbo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.tubespbo.tubespbo.exception.ApiException;
import com.example.tubespbo.tubespbo.model.response.ApiResponse;
import com.example.tubespbo.tubespbo.service.PemesananService;


@RestController
@RequestMapping("/pemesanan")
@RequiredArgsConstructor
public class PemesananController {

    private final PemesananRepository pemesananRepository;
    private final UserRepository userRepository;
    private final JadwalRepository jadwalRepository;

    @Autowired
    private ApiResponseMapper responseBuilder;

    @PostMapping
    @PreAuthorize("isAutenticated()")
    public ResponseEntity<ApiResponse<PemesananResponse>> createPemesanan(PemesananRequest request) {
        try {
            PemesananResponse result = PemesananService.createPemesanan(request);
            ApiResponse<PemesananResponse> response = responseBuilder.ToApiResponse(HttpStatus.OK, "Data kereta berhasil dimasukkan", result);
            return ResponseEntity.ok(response);
        } catch (ApiException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiException("Terjadi kesalahan dalama memasukkan data kereta");
        }
    }
    
    // @PostMapping
    // public ResponseEntity<?> createPemesanan(
    //         @RequestParam Long penumpangId,
    //         @RequestParam Long jadwalId,
    //         @RequestParam String kelas,
    //         @RequestParam double harga
    // ) {
    //     Optional<UserEntity> optionalUser = userRepository.findById(penumpangId);
    //     Optional<Jadwal> optionalJadwal = jadwalRepository.findById(jadwalId);

    //     if (optionalUser.isEmpty() || !(optionalUser.get() instanceof PenumpangEntity)) {
    //         return ResponseEntity.badRequest().body("Penumpang dengan ID tersebut tidak ditemukan atau bukan penumpang.");
    //     }

    //     if (optionalJadwal.isEmpty()) {
    //         return ResponseEntity.badRequest().body("Jadwal dengan ID tersebut tidak ditemukan.");
    //     }

    //     PenumpangEntity penumpang = (PenumpangEntity) optionalUser.get();

    //     PemesananEntity pemesanan = PemesananEntity.builder()
    //             .kelas(kelas)
    //             .harga(harga)
    //             .penumpang(penumpang)
    //             .jadwal(optionalJadwal.get())
    //             .build();

    //     pemesananRepository.save(pemesanan);

    //     PemesananResponse response = PemesananResponseMapper.toPemesananResponse(pemesanan);
    //     return ResponseEntity.ok(response);
    // }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPemesananById(@PathVariable Long id) {
        Optional<PemesananEntity> optionalPemesanan = pemesananRepository.findById(id);
        if (optionalPemesanan.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PemesananResponse response = PemesananResponseMapper.toPemesananResponse(optionalPemesanan.get());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePemesanan(@PathVariable Long id) {
        if (!pemesananRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pemesananRepository.deleteById(id);
        return ResponseEntity.ok("Pemesanan dengan ID " + id + " berhasil dihapus.");
    }
}
