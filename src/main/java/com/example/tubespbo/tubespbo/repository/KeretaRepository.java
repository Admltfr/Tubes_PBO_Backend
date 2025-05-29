package com.example.tubespbo.tubespbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tubespbo.tubespbo.entity.KeretaEntity;

public interface KeretaRepository extends JpaRepository<KeretaEntity, Long> {
    KeretaEntity findByAsal(String asal);
    KeretaEntity findByTujuan(String tujuan);
}
