package com.example.tubespbo.tubespbo.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.tubespbo.tubespbo.entity.PemesananEntity;
import com.example.tubespbo.tubespbo.model.response.PemesananResponse;

@Component
public class PemesananResponseMapper {

    private final JadwalResponseMapper jadwalResponseMapper;
    private final UserMapper userMapper;

    @Autowired
    public PemesananResponseMapper(JadwalResponseMapper jadwalResponseMapper, UserMapper userMapper) {
        this.jadwalResponseMapper = jadwalResponseMapper;
        this.userMapper = userMapper;
    }

    public PemesananResponse toPemesananResponse(PemesananEntity pemesanan) {
        return PemesananResponse.builder()
                .harga(pemesanan.getHarga())
                .kelas(pemesanan.getKelas())
                .jadwal(jadwalResponseMapper.toJadwalResponse(pemesanan.getJadwal()))
                .penumpang(userMapper.ToPenumpangResponseMapper(pemesanan.getPenumpang()))
                .build();
    }
}
