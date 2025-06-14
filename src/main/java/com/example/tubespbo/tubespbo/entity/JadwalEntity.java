package com.example.tubespbo.tubespbo.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "jadwal_rute", joinColumns = @JoinColumn(name = "jadwal_id"))
    @Column(name = "rute")
    private List<String> rute;

    @ManyToOne
    @JoinColumn(name = "kereta_id", nullable = false)
    private KeretaEntity kereta;

    @OneToMany(mappedBy = "jadwal", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PemesananEntity> pemesananList;
}
