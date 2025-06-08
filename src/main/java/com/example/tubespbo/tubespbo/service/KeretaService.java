package com.example.tubespbo.tubespbo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tubespbo.tubespbo.entity.KeretaEntity;
import com.example.tubespbo.tubespbo.exception.ApiException;
import com.example.tubespbo.tubespbo.mapper.KeretaResponseMapper;
import com.example.tubespbo.tubespbo.model.request.KeretaRequest;
import com.example.tubespbo.tubespbo.model.request.KeretaUpdateRequest;
import com.example.tubespbo.tubespbo.model.response.KeretaResponse;
import com.example.tubespbo.tubespbo.repository.KeretaRepository;

import jakarta.transaction.Transactional;

@Service
public class KeretaService {
    @Autowired
    private KeretaRepository keretaRepository;

    @Autowired
    private KeretaResponseMapper keretaMapper;

    public List<KeretaResponse> getAllKereta() {
        return keretaRepository.findAll()
            .stream()
            .map(keretaMapper::toKeretaResponse)
            .collect(Collectors.toList());
    }

    public KeretaResponse getKeretaByAsal(String asal) {
        KeretaEntity kereta = keretaRepository.findByAsal(asal);
        if (kereta == null) return null;
        return keretaMapper.toKeretaResponse(kereta);
    }

    public KeretaResponse getKeretaById(Long id) {
        Optional<KeretaEntity> kereta = keretaRepository.findById(id);
        if (kereta.isEmpty()) {
            throw new ApiException("kereta dengan ID " + id + " tidak ditemukan.");
        }
        return keretaMapper.toKeretaResponse(kereta.get());
    }

    public KeretaResponse getKeretaByTujuan(String tujuan) {
        KeretaEntity kereta = keretaRepository.findByTujuan(tujuan);
        if (kereta == null) return null;
        return keretaMapper.toKeretaResponse(kereta);
    }

    @Transactional
    public KeretaResponse createKereta(KeretaRequest request) {
        KeretaEntity entity = keretaMapper.toKeretaEntity(request);
        keretaRepository.save(entity);
        return keretaMapper.toKeretaResponse(entity);
    }

    @Transactional
    public KeretaResponse updateKereta(KeretaUpdateRequest request) {
        Long keretaId = request.getId();
        KeretaEntity entity = keretaRepository.findById(keretaId)
            .orElseThrow(() -> new ApiException("Data kereta dengan ID " + request.getId() + " tidak ditemukan"));
        
        entity.setAsal(request.getAsal());
        entity.setTujuan(request.getTujuan());

        KeretaEntity updated = keretaRepository.save(entity);
        return keretaMapper.toKeretaResponse(updated);
    }

    @Transactional
    public KeretaResponse deleteKereta(Long id) {
        KeretaEntity entity = keretaRepository.findById(id)
            .orElseThrow(() -> new ApiException("Data kereta dengan ID " + id + " tidak ditemukan"));

        keretaRepository.delete(entity);
        return keretaMapper.toKeretaResponse(entity);
    }
}
