package com.example.tubespbo.tubespbo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Kereta {

    @Id
    private String id;

    private String asal;
    private String tujuan;

    public Kereta() {}

    public Kereta(String id, String asal, String tujuan) {
        this.id = id;
        this.asal = asal;
        this.tujuan = tujuan;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }
}
