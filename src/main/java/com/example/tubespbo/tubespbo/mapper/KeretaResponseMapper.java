package com.example.tubespbo.tubespbo.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.tubespbo.tubespbo.entity.KeretaEntity;
import com.example.tubespbo.tubespbo.model.request.KeretaRequest;
import com.example.tubespbo.tubespbo.model.response.KeretaResponse;

@Component
public class KeretaResponseMapper {
    public KeretaEntity toKeretaEntity(KeretaRequest request) {
        return KeretaEntity.builder()
            .asal(request.getAsal())
            .tujuan(request.getTujuan())
            .build();
    }

    public KeretaResponse toKeretaResponse(KeretaEntity entity) {
        return KeretaResponse.builder()
            .asal(entity.getAsal())
            .tujuan(entity.getTujuan())
            .build();
    }

    // public KeretaResponse toKeretaResponse2(Optional<KeretaEntity> kereta) {
    //     return KeretaResponse.builder()
    //         .asal(kereta.getAsal())
    //         .tujuan(kereta.getTujuan())
    //         .build();
    // }
}