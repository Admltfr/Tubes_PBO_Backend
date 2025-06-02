package com.example.tubespbo.tubespbo.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.tubespbo.tubespbo.entity.PemesananEntity;
import com.example.tubespbo.tubespbo.model.request.PemesananRequest;
import com.example.tubespbo.tubespbo.model.response.PemesananResponse;

@Component
public class PemesananMapper {

    @Autowired
    private JadwalResponseMapper jadwalResponseMapper;

    @Autowired
    private UserMapper userMapper;

    public PemesananResponse toPemesananResponse(PemesananEntity pemesanan) {
        return PemesananResponse.builder()
                .harga(pemesanan.getHarga())
                .kelas(pemesanan.getKelas())
                .jadwal(jadwalResponseMapper.toJadwalResponse(pemesanan.getJadwal()))
                .penumpang(userMapper.ToPenumpangResponseMapper(pemesanan.getPenumpang()))
                .build();
    }

    public PemesananRequest toPemesananRequest(PemesananRequest request) {
        return PemesananRequest.builder()
                .harga(request.getHarga())
                .kelas(request.getKelas())
                .jadwalId(request.getJadwalId())
                .penumpangId(request.getPenumpangId())
                .build();
    }

    public PemesananEntity toPemesananEntity(PemesananEntity request) {
        return PemesananEntity.builder()
                .harga(request.getHarga())
                .kelas(request.getKelas())
                .jadwal(request.getJadwal())
                .penumpang(request.getPenumpang())
                .build();
    }
}
