package com.example.tubespbo.tubespbo.model.request;

import java.util.Date;
import java.util.List;

public class JadwalRequest {
    private String keretaId;
    private Date tanggal;
    private Date waktuKeberangkatan;
    private List<String> rute;

    // Getter & Setter
    public String getKeretaId() {
        return keretaId;
    }
    public void setKeretaId(String keretaId) {
        this.keretaId = keretaId;
    }

    public Date getTanggal() {
        return tanggal;
    }
    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Date getWaktuKeberangkatan() {
        return waktuKeberangkatan;
    }
    public void setWaktuKeberangkatan(Date waktuKeberangkatan) {
        this.waktuKeberangkatan = waktuKeberangkatan;
    }

    public List<String> getRute() {
        return rute;
    }
    public void setRute(List<String> rute) {
        this.rute = rute;
    }
}
