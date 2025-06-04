package com.example.tubespbo.tubespbo.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PemesananResponse {
    private double harga;
    private String kelas;
    private JadwalResponse jadwal;  
    private PenumpangResponse penumpang;
}

