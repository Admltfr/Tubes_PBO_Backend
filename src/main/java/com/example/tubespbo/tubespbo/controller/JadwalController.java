package com.example.tubespbo.tubespbo.controller;

import com.example.tubespbo.tubespbo.entity.Jadwal;
import com.example.tubespbo.tubespbo.entity.Kereta;
import com.example.tubespbo.tubespbo.repository.JadwalRepository;
import com.example.tubespbo.tubespbo.repository.KeretaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// import com.example.tubespbo.tubespbo.model.request.KeretaRequest;
// import com.example.tubespbo.tubespbo.model.request.JadwalRequest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jadwal")
public class JadwalController {

    @Autowired
    private JadwalRepository jadwalRepo;

    @Autowired
    private KeretaRepository keretaRepo;

    // Lihat semua jadwal
    @GetMapping("/list")
    public List<Jadwal> getAllJadwal() {
        return jadwalRepo.findAll();
    }

    // Tambah jadwal
    @PostMapping("/add")
    public Jadwal addJadwal(@RequestParam String keretaId,
                            @RequestParam Date tanggal,
                            @RequestParam Date waktuKeberangkatan,
                            @RequestBody List<String> rute) {

        Optional<Kereta> keretaOpt = keretaRepo.findById(keretaId);
        if (keretaOpt.isEmpty()) {
            throw new RuntimeException("Kereta tidak ditemukan");
        }

        Jadwal jadwal = new Jadwal(tanggal, waktuKeberangkatan, rute.toArray(new String[0]), keretaOpt.get());
        return jadwalRepo.save(jadwal);
    }

    // Hapus jadwal
    @DeleteMapping("/delete/{id}")
    public void deleteJadwal(@PathVariable String id) {
        jadwalRepo.deleteById(id);
    }

    // Update jadwal
    @PutMapping("/update/{id}")
    public Jadwal updateJadwal(@PathVariable String id,
                               @RequestParam Date tanggal,
                               @RequestParam Date waktuKeberangkatan,
                               @RequestBody List<String> rute) {
        return jadwalRepo.findById(id).map(j -> {
            j.setTanggal(tanggal);
            j.setWaktuKeberangkatan(waktuKeberangkatan);
            j.setRute(rute.toArray(new String[0]));
            return jadwalRepo.save(j);
        }).orElseThrow();
    }
}
