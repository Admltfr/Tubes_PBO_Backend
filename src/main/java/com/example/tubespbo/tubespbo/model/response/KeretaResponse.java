package com.example.tubespbo.tubespbo.model.response;

public class KeretaResponse {
    private String id;
    private String asal;
    private String tujuan;

    public KeretaResponse(String id, String asal, String tujuan) {
        this.id = id;
        this.asal = asal;
        this.tujuan = tujuan;
    }

    // Getter
    public String getId() {
        return id;
    }

    public String getAsal() {
        return asal;
    }

    public String getTujuan() {
        return tujuan;
    }
}