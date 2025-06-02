package com.example.tubespbo.tubespbo.service;

import com.example.tubespbo.tubespbo.entity.JadwalEntity;
import com.example.tubespbo.tubespbo.entity.KeretaEntity;
import com.example.tubespbo.tubespbo.mapper.JadwalResponseMapper;
import com.example.tubespbo.tubespbo.model.request.JadwalRequest;
import com.example.tubespbo.tubespbo.model.response.JadwalResponse;
import com.example.tubespbo.tubespbo.repository.JadwalRepository;
import com.example.tubespbo.tubespbo.repository.KeretaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JadwalService {

    private final JadwalRepository jadwalRepository;
    private final KeretaRepository keretaRepository;

    public List<JadwalResponse> getAll() {
        List<JadwalEntity> jadwals = jadwalRepository.findAll();
        return JadwalResponseMapper.toResponseList(jadwals);
    }

    public JadwalResponse add(JadwalRequest req) {
        KeretaEntity kereta = keretaRepository.findById(req.getKeretaId())
                .orElseThrow(() -> new IllegalArgumentException("Kereta tidak ditemukan"));

        JadwalEntity jadwal = JadwalEntity.builder()
                .tanggal(req.getTanggal())
                .waktuKeberangkatan(req.getWaktuKeberangkatan())
                .rute(req.getRute())
                .kereta(kereta)
                .build();

        JadwalEntity saved = jadwalRepository.save(jadwal);
        return JadwalResponseMapper.toResponse(saved);
    }

    public void delete(Long id) {
        if (!jadwalRepository.existsById(id)) {
            throw new IllegalArgumentException("Jadwal dengan ID tersebut tidak ditemukan");
        }
        jadwalRepository.deleteById(id);
    }

    public JadwalResponse update(Long id, JadwalRequest req) {
        JadwalEntity jadwal = jadwalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Jadwal tidak ditemukan"));

        KeretaEntity kereta = keretaRepository.findById(req.getKeretaId())
                .orElseThrow(() -> new IllegalArgumentException("Kereta tidak ditemukan"));

        jadwal.setTanggal(req.getTanggal());
        jadwal.setWaktuKeberangkatan(req.getWaktuKeberangkatan());
        jadwal.setRute(req.getRute());
        jadwal.setKereta(kereta);

        JadwalEntity updated = jadwalRepository.save(jadwal);
        return JadwalResponseMapper.toResponse(updated);
    }
}
