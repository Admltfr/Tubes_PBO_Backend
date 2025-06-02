package com.example.tubespbo.tubespbo.controller;

import com.example.tubespbo.tubespbo.model.request.JadwalRequest;
import com.example.tubespbo.tubespbo.model.response.JadwalResponse;
import com.example.tubespbo.tubespbo.service.JadwalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jadwal")
@RequiredArgsConstructor
public class JadwalController {

    private final JadwalService jadwalService;

    @GetMapping("/list")
    public ResponseEntity<List<JadwalResponse>> getAllJadwal() {
        List<JadwalResponse> jadwals = jadwalService.getAll();
        return ResponseEntity.ok(jadwals);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addJadwal(@Valid @RequestBody JadwalRequest req) {
        try {
            JadwalResponse saved = jadwalService.add(req);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Gagal menambahkan jadwal: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Terjadi kesalahan server.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteJadwal(@PathVariable Long id) {
        try {
            jadwalService.delete(id);
            return ResponseEntity.ok("Jadwal berhasil dihapus");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Gagal menghapus jadwal: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Terjadi kesalahan server.");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateJadwal(@PathVariable Long id, @Valid @RequestBody JadwalRequest req) {
        try {
            JadwalResponse updated = jadwalService.update(id, req);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Gagal mengupdate jadwal: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Terjadi kesalahan server.");
        }
    }
}
