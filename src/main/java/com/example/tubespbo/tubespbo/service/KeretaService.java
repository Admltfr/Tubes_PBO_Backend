package com.example.tubespbo.tubespbo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tubespbo.tubespbo.entity.KeretaEntity;
import com.example.tubespbo.tubespbo.mapper.KeretaResponseMapper;
import com.example.tubespbo.tubespbo.model.response.KeretaResponse;
import com.example.tubespbo.tubespbo.repository.KeretaRepository;

@Service
public class KeretaService {
    @Autowired
    private KeretaRepository keretaRepository;

    public List<KeretaResponse> getAllKereta() {
        return keretaRepository.findAll()
            .stream()
            .map(KeretaResponseMapper::ToKeretaResponseMapper)
            .collect(Collectors.toList());
    }

    public KeretaResponse getKeretaByAsal(String asal) {
        KeretaEntity kereta = keretaRepository.findByAsal(asal);
        if (kereta == null) return null;
        return KeretaResponseMapper.ToKeretaResponseMapper(kereta);
    }

    public KeretaResponse getKeretaByTujuan(String tujuan) {
        KeretaEntity kereta = keretaRepository.findByTujuan(tujuan);
        if (kereta == null) return null;
        return KeretaResponseMapper.ToKeretaResponseMapper(kereta);
    }
}
