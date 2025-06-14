package com.example.tubespbo.tubespbo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.tubespbo.tubespbo.entity.JadwalEntity;
import com.example.tubespbo.tubespbo.model.response.JadwalResponse;

@Component
public class JadwalResponseMapper {

    public JadwalResponse toResponse(JadwalEntity entity) {
        if (entity == null) {
            return null;
        }
        return JadwalResponse.builder()
            .id(entity.getId())
            .waktuKeberangkatan(entity.getWaktuKeberangkatan())
            .rute(entity.getRute())
            .keretaId(entity.getKereta() != null ? entity.getKereta().getId() : null)
            .harga(entity.getHarga())
            .kelas(entity.getKelas())
            .build();
    }

    public List<JadwalResponse> toResponseList(List<JadwalEntity> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
