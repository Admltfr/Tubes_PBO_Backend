package com.example.tubespbo.tubespbo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

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

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date tanggal;

    @Temporal(TemporalType.TIME)
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
