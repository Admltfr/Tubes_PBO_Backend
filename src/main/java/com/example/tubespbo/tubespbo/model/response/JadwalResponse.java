package com.example.tubespbo.tubespbo.model.response;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JadwalResponse {
    private Long id;
    private Date waktuKeberangkatan;
    private List<String> rute;
    private Long keretaId;
    private double harga;
    private String kelas;
}
