package com.example.tubespbo.tubespbo.mapper;

import com.example.tubespbo.tubespbo.entity.KeretaEntity;
import com.example.tubespbo.tubespbo.model.response.KeretaResponse;

public class KeretaResponseMapper {
    public static KeretaResponse ToKeretaResponseMapper(KeretaEntity kereta) {
        return KeretaResponse.builder().asal(kereta.getAsal()).tujuan(kereta.getTujuan()).build();
    }
}
