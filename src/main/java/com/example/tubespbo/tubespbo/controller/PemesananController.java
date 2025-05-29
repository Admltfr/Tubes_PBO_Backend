package com.example.tubespbo.tubespbo.controller;

//import com.example.tubespbo.tubespbo.entity.Jadwal;
import com.example.tubespbo.tubespbo.entity.PemesananEntity;
//import com.example.tubespbo.tubespbo.entity.PenumpangEntity;
//import com.example.tubespbo.tubespbo.entity.UserEntity;
import com.example.tubespbo.tubespbo.mapper.PemesananResponseMapper;
import com.example.tubespbo.tubespbo.model.response.PemesananResponse;
//import com.example.tubespbo.tubespbo.repository.JadwalRepository;
import com.example.tubespbo.tubespbo.repository.PemesananRepository;
//import com.example.tubespbo.tubespbo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pemesanan")
@RequiredArgsConstructor
public class PemesananController {

    private final PemesananRepository pemesananRepository;
    private final UserRepository userRepository;
    private final JadwalRepository jadwalRepository;

    @PostMapping
    public ResponseEntity<?> createPemesanan(
            @RequestParam Long penumpangId,
            @RequestParam String jadwalId,
            @RequestParam String kelas,
            @RequestParam double harga
    ) {
        Optional<UserEntity> optionalUser = userRepository.findById(penumpangId);
        Optional<Jadwal> optionalJadwal = jadwalRepository.findById(jadwalId);

        if (optionalUser.isEmpty() || !(optionalUser.get() instanceof PenumpangEntity)) {
            return ResponseEntity.badRequest().body("Penumpang dengan ID tersebut tidak ditemukan atau bukan penumpang.");
        }

        if (optionalJadwal.isEmpty()) {
            return ResponseEntity.badRequest().body("Jadwal dengan ID tersebut tidak ditemukan.");
        }

        PenumpangEntity penumpang = (PenumpangEntity) optionalUser.get();

        PemesananEntity pemesanan = PemesananEntity.builder()
                .kelas(kelas)
                .harga(harga)
                .penumpang(penumpang)
                .jadwal(optionalJadwal.get())
                .build();

        pemesananRepository.save(pemesanan);

        PemesananResponse response = PemesananResponseMapper.toPemesananResponse(pemesanan);
        return ResponseEntity.ok(response);
    }

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
