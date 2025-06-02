package com.example.tubespbo.tubespbo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "pemesanan")
public class PemesananEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double harga;

    private String kelas;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude  // mencegah loop infinite saat toString() semoga works
    private PenumpangEntity penumpang;

    @ManyToOne
    @ToString.Exclude
    private JadwalEntity jadwal;

    public Object getJumlahTiket() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getJumlahTiket'");
    }

    public Object getWaktuPemesanan() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWaktuPemesanan'");
    }

    public Object getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWaktuPemesanan'");
    }
}
