package com.example.tubespbo.tubespbo.mapper;

import com.example.tubespbo.tubespbo.entity.PemesananEntity;
import com.example.tubespbo.tubespbo.model.response.PemesananResponse;

import java.util.List;
import java.util.stream.Collectors;

public class PemesananResponseMapper {

    public static PemesananResponse toResponse(PemesananEntity entity) {
        if (entity == null) {
            return null;
        }

        return PemesananResponse.builder()
                .id(entity.getId())
                .jadwalId(entity.getJadwal() != null ? entity.getJadwal().getId() : null)
                .penumpangId(entity.getPenumpang() != null ? entity.getPenumpang().getId() : null)
                .waktuPemesanan(entity.getWaktuPemesanan())
                .jumlahTiket(entity.getJumlahTiket())
                .build();
    }

    public static List<PemesananResponse> toResponseList(List<PemesananEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(PemesananResponseMapper::toResponse)
                .collect(Collectors.toList());
    }
}
