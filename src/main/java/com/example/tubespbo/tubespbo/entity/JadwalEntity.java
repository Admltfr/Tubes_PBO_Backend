package com.example.tubespbo.tubespbo.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "jadwal")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JadwalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double harga;

    private String kelas;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date waktuKeberangkatan;

    @ElementCollection
    @CollectionTable(name = "jadwal_rute", joinColumns = @JoinColumn(name = "jadwal_id"))
    @Column(name = "rute")
    private List<String> rute;

    @ManyToOne
    @JoinColumn(name = "kereta_id", nullable = false)
    private KeretaEntity kereta;
}
