package com.example.tubespbo.tubespbo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tubespbo.tubespbo.entity.JadwalEntity;
import com.example.tubespbo.tubespbo.entity.PemesananEntity;
import com.example.tubespbo.tubespbo.entity.PenumpangEntity;
import com.example.tubespbo.tubespbo.entity.UserEntity;
import com.example.tubespbo.tubespbo.exception.ApiException;
import com.example.tubespbo.tubespbo.mapper.PemesananMapper;
import com.example.tubespbo.tubespbo.model.request.PemesananRequest;
import com.example.tubespbo.tubespbo.model.request.PemesananUpdateRequest;
import com.example.tubespbo.tubespbo.model.response.PemesananResponse;
import com.example.tubespbo.tubespbo.repository.JadwalRepository;
import com.example.tubespbo.tubespbo.repository.PemesananRepository;
import com.example.tubespbo.tubespbo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class PemesananService {

    @Autowired
    private PemesananRepository pemesananRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JadwalRepository jadwalRepository;

    @Autowired
    private PemesananMapper pemesananMapper;

    public PemesananResponse getPemesananById(Long id) {
        Optional<PemesananEntity> optionalPemesanan = pemesananRepository.findById(id);
        if (optionalPemesanan.isEmpty()) {
            throw new ApiException("Pemesanan dengan ID " + id + " tidak ditemukan.");
        }
        return pemesananMapper.toPemesananResponse(optionalPemesanan.get());
    }

    public List<PemesananResponse> getAllPemesanan() {
        return pemesananRepository.findAll()
            .stream()
            .map(pemesananMapper::toPemesananResponse)
            .collect(Collectors.toList());
    }

    @Transactional
    public PemesananResponse createPemesanan(PemesananRequest request) {
        Optional<UserEntity> optionalUser = userRepository.findById(request.getPenumpangId());
        if (optionalUser.isEmpty() || !(optionalUser.get() instanceof PenumpangEntity)) {
            throw new ApiException("Penumpang dengan ID " + request.getPenumpangId() + " tidak ditemukan atau bukan penumpang.");
        }
        PenumpangEntity penumpang = (PenumpangEntity) optionalUser.get();

        Optional<JadwalEntity> optionalJadwal = jadwalRepository.findById(request.getJadwalId());
        if (optionalJadwal.isEmpty()) {
            throw new ApiException("Jadwal dengan ID " + request.getJadwalId() + " tidak ditemukan.");
        }
        JadwalEntity jadwal = optionalJadwal.get();

        PemesananEntity pemesanan = new PemesananEntity();
        pemesanan.setPenumpang(penumpang);
        pemesanan.setJadwal(jadwal);

        PemesananEntity saved = pemesananRepository.save(pemesanan);

        return pemesananMapper.toPemesananResponse(saved);
    }

    @Transactional
    public void deletePemesanan(Long id) {
        if (!pemesananRepository.existsById(id)) {
            throw new ApiException("Pemesanan dengan ID " + id + " tidak ditemukan.");
        }
        pemesananRepository.deleteById(id);
    }

    @Transactional
    public PemesananResponse updatePemesanan(PemesananUpdateRequest request) {
        // Long PemesananId = request.getId();
        // PemesananEntity entity = pemesananRepository.findById(PemesananId)
        //     .orElseThrow(() -> new ApiException("Data kereta dengan ID " + request.getId() + " tidak ditemukan"));
        
        // // entity.setPenumpangId(request.getPenumpangId());
        // // entity.setTujuan(request.getJadwalId());
        // entity.setPenumpang(request.getJadwalId());
        // entity.setJadwal(request.getJadwalId());

        // PemesananEntity updated = PemesananRepository.save(entity);
        // return PemesananMapper.toPemesananResponse(updated);
        Optional<UserEntity> optionalUser = userRepository.findById(request.getPenumpangId());
        if (optionalUser.isEmpty() || !(optionalUser.get() instanceof PenumpangEntity)) {
            throw new ApiException("Penumpang dengan ID " + request.getPenumpangId() + " tidak ditemukan atau bukan penumpang.");
        }
        PenumpangEntity penumpang = (PenumpangEntity) optionalUser.get();

        Optional<JadwalEntity> optionalJadwal = jadwalRepository.findById(request.getJadwalId());
        if (optionalJadwal.isEmpty()) {
            throw new ApiException("Jadwal dengan ID " + request.getJadwalId() + " tidak ditemukan.");
        }
        JadwalEntity jadwal = optionalJadwal.get();
        
        PemesananEntity pemesanan = new PemesananEntity();
        pemesanan.setPenumpang(penumpang);
        pemesanan.setJadwal(jadwal);

        PemesananEntity saved = pemesananRepository.save(pemesanan);

        return pemesananMapper.toPemesananResponse(saved);
    }
}
