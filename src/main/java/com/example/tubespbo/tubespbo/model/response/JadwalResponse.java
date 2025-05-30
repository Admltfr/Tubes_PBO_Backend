package com.example.tubespbo.tubespbo.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JadwalResponse {
    private Long id;
    private Date tanggal;
    private Date waktuKeberangkatan;
    private List<String> rute;
    private String keretaId;
}
