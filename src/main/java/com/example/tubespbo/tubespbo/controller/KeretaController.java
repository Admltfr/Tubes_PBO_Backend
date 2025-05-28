package com.example.tubespbo.tubespbo.controller;

import com.example.tubespbo.tubespbo.entity.Kereta;
import com.example.tubespbo.tubespbo.repository.KeretaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// import com.example.tubespbo.tubespbo.model.request.KeretaRequest;
// import com.example.tubespbo.tubespbo.model.request.JadwalRequest;


import java.util.List;

@RestController
@RequestMapping("/kereta")
public class KeretaController {

    @Autowired
    private KeretaRepository keretaRepo;

    // USER - lihat semua kereta
    @GetMapping("/list")
    public List<Kereta> getAllKereta() {
        return keretaRepo.findAll();
    }

    // ADMIN - tambah kereta
    @PostMapping("/add")
    public Kereta addKereta(@RequestBody Kereta k) {
        return keretaRepo.save(k);
    }

    // ADMIN - hapus kereta
    @DeleteMapping("/delete/{id}")
    public void deleteKereta(@PathVariable String id) {
        keretaRepo.deleteById(id);
    }

    // ADMIN - update kereta
    @PutMapping("/update/{id}")
    public Kereta updateKereta(@PathVariable String id, @RequestBody Kereta updated) {
        return keretaRepo.findById(id).map(k -> {
            k.setAsal(updated.getAsal());
            k.setTujuan(updated.getTujuan());
            return keretaRepo.save(k);
        }).orElseThrow();
    }
}
