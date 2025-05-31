package com.example.tubespbo.tubespbo.mapper;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.example.tubespbo.tubespbo.entity.Jadwal;
import com.example.tubespbo.tubespbo.model.response.JadwalResponse;

@Component
public class JadwalResponseMapper {

    public JadwalResponse toJadwalResponse(Jadwal jadwal) {
        if (jadwal == null) {
            return null;
        }

        return JadwalResponse.builder()
                .id(jadwal.getId())
                .tanggal(jadwal.getTanggal())
                .waktuKeberangkatan(jadwal.getWaktuKeberangkatan())
                .rute(Arrays.asList(jadwal.getRute()))
                .keretaId(jadwal.getKereta().getId())
                .build();
    }
}
