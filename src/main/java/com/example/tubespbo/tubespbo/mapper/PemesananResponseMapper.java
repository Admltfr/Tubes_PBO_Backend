package com.example.tubespbo.tubespbo.mapper;

import com.example.tubespbo.tubespbo.entity.PemesananEntity;
import com.example.tubespbo.tubespbo.model.response.PemesananResponse;
import com.example.tubespbo.tubespbo.mapper.JadwalResponseMapper;
import com.example.tubespbo.tubespbo.mapper.response.PenumpangResponseMapper;

public class PemesananResponseMapper {

    public static PemesananResponse toPemesananResponse(PemesananEntity pemesanan) {
        return PemesananResponse.builder()
                .harga(pemesanan.getHarga())
                .kelas(pemesanan.getKelas())
                .jadwal(JadwalResponseMapper.ToJadwalResponse(pemesanan.getJadwal()))
                .penumpang(PenumpangResponseMapper.ToPenumpangResponseMapper(pemesanan.getPenumpang()))
                .build();
    }
}
