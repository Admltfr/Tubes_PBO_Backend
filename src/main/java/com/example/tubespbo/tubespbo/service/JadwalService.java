package com.example.tubespbo.tubespbo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tubespbo.tubespbo.entity.JadwalEntity;
import com.example.tubespbo.tubespbo.entity.KeretaEntity;
import com.example.tubespbo.tubespbo.exception.ApiException;
import com.example.tubespbo.tubespbo.mapper.JadwalResponseMapper;
import com.example.tubespbo.tubespbo.model.request.JadwalRequest;
import com.example.tubespbo.tubespbo.model.response.JadwalResponse;
import com.example.tubespbo.tubespbo.model.response.KeretaResponse;
import com.example.tubespbo.tubespbo.repository.JadwalRepository;
import com.example.tubespbo.tubespbo.repository.KeretaRepository;

import jakarta.transaction.Transactional;

@Service
public class JadwalService {

    @Autowired
    private JadwalRepository jadwalRepository;

    @Autowired
    private KeretaRepository keretaRepository;

    @Autowired
    private JadwalResponseMapper jadwalResponseMapper;

    public List<JadwalResponse> getAll() {
        List<JadwalEntity> jadwals = jadwalRepository.findAll();
        return jadwalResponseMapper.toResponseList(jadwals);
    }

    @Transactional
    public JadwalResponse add(JadwalRequest req) {
        Optional<KeretaEntity> optionalKereta = keretaRepository.findById(req.getKeretaId());
        if (optionalKereta.isEmpty()) {
            throw new ApiException("Kereta dengan ID " + req.getKeretaId() + " tidak ditemukan.");
        }
        KeretaEntity kereta = optionalKereta.get();

        JadwalEntity jadwal = JadwalEntity.builder()
                .waktuKeberangkatan(req.getWaktuKeberangkatan())
                .rute(req.getRute())
                .kereta(kereta)
                .harga(req.getHarga())
                .kelas(req.getKelas())
                .build();

        JadwalEntity saved = jadwalRepository.save(jadwal);
        return jadwalResponseMapper.toResponse(saved);
    }

    public JadwalResponse getJadwalById(Long id) {
        Optional<JadwalEntity> optionalJadwal = jadwalRepository.findById(id);
        if (optionalJadwal.isEmpty()) {
            throw new ApiException("Jadwal dengan ID " + id + " tidak ditemukan.");
        }
        return jadwalResponseMapper.toResponse(optionalJadwal.get());
    }

    @Transactional
    public JadwalResponse deleteJadwal(Long id) {
        JadwalEntity entity = jadwalRepository.findById(id)
            .orElseThrow(() -> new ApiException("Data jadwal dengan ID " + id + " tidak ditemukan"));

        jadwalRepository.delete(entity);
        return jadwalResponseMapper.toResponse(entity);
    }

    @Transactional
    public JadwalResponse updateJadwal(Long id, JadwalRequest req) {
        Optional<JadwalEntity> optionalJadwal = jadwalRepository.findById(id);
        if (optionalJadwal.isEmpty()) {
            throw new ApiException("Jadwal dengan ID " + id + " tidak ditemukan.");
        }
        JadwalEntity jadwal = optionalJadwal.get();

        Optional<KeretaEntity> optionalKereta = keretaRepository.findById(req.getKeretaId());
        if (optionalKereta.isEmpty()) {
            throw new ApiException("Kereta dengan ID " + req.getKeretaId() + " tidak ditemukan.");
        }
        KeretaEntity kereta = optionalKereta.get();

        jadwal.setWaktuKeberangkatan(req.getWaktuKeberangkatan());
        jadwal.setRute(req.getRute());
        jadwal.setKereta(kereta);
        jadwal.setHarga(req.getHarga());
        jadwal.setKelas(req.getKelas());

        JadwalEntity updated = jadwalRepository.save(jadwal);
        return jadwalResponseMapper.toResponse(updated);
    }
}