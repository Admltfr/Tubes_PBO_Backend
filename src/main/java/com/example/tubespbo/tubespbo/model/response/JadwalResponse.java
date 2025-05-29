package com.example.tubespbo.tubespbo.model.response;

import java.util.Date;
import java.util.List;

public class JadwalResponse {
    private String id;
    private Date tanggal;
    private Date waktuKeberangkatan;
    private List<String> rute;
    private String keretaId;

    public JadwalResponse(String id, Date tanggal, Date waktuKeberangkatan, List<String> rute, String keretaId) {
        this.id = id;
        this.tanggal = tanggal;
        this.waktuKeberangkatan = waktuKeberangkatan;
        this.rute = rute;
        this.keretaId = keretaId;
    }

    public String getId() {
        return id;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public Date getWaktuKeberangkatan() {
        return waktuKeberangkatan;
    }

    public List<String> getRute() {
        return rute;
    }

    public String getKeretaId() {
        return keretaId;
    }
}

