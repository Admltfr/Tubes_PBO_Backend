package com.example.tubespbo.tubespbo.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Jadwal {

    @Id
    //@GeneratedValue(strategy = GenerationType.UUID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggal;

    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuKeberangkatan;

    private String[] rute;

    @ManyToOne
    @JoinColumn(name = "kereta_id")
    private KeretaEntity kereta;

    public Jadwal() {}

    public Jadwal(Date tanggal, Date waktuKeberangkatan, String[] rute, KeretaEntity kereta) {
        this.tanggal = tanggal;
        this.waktuKeberangkatan = waktuKeberangkatan;
        this.rute = rute;
        this.kereta = kereta;
    }

    public Long getId() {
        return id;
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

    public String[] getRute() {
        return rute;
    }

    public void setRute(String[] rute) {
        this.rute = rute;
    }

    public KeretaEntity getKereta() {
        return kereta;
    }

    public void setKereta(KeretaEntity kereta) {
        this.kereta = kereta;
    }

    public String getWaktu() {
        return waktuKeberangkatan != null ? waktuKeberangkatan.toString() : null;
    }

    public String getKeretaId() {
        if (kereta != null && kereta.getId() != null) {
            return String.valueOf(kereta.getId());
        }
        return null;
    }
}
