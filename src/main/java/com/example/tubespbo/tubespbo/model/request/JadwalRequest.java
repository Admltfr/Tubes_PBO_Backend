package com.example.tubespbo.tubespbo.model.request;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotNull;

public class JadwalRequest {

    @NotNull(message = "ID penumpang tidak boleh kosong")
    private Long keretaId;

    @NotNull(message = "Tanggal tidak boleh kosong")
    private Date tanggal;

    @NotNull(message = "Waktu keberangkatan tidak boleh kosong")
    private Date waktuKeberangkatan;

    @NotNull(message = "Rute tidak boleh kosong")
    private List<String> rute;

    public Long getKeretaId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getKeretaId'");
    }

    public Date getWaktuKeberangkatan() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWaktuKeberangkatan'");
    }

    public Date getTanggal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTanggal'");
    }

    public List<String> getRute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRute'");
    }
    

}
