package com.example.tubespbo.tubespbo.mapper;

import com.example.tubespbo.tubespbo.entity.JadwalEntity;
import com.example.tubespbo.tubespbo.model.response.JadwalResponse;

import java.util.List;
import java.util.stream.Collectors;

public class JadwalResponseMapper {

    public JadwalResponse toResponse(JadwalEntity entity) {
        if (entity == null) {
            return null;
        }

        return JadwalResponse.builder()
                .id(entity.getId())
                .tanggal(entity.getTanggal())
                .waktuKeberangkatan(entity.getWaktuKeberangkatan())
                .rute(entity.getRute())
                .keretaId(entity.getKereta() != null ? entity.getKereta().getId() : null)
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
