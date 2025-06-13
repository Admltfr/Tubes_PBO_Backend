package com.example.tubespbo.tubespbo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tubespbo.tubespbo.entity.PemesananEntity;

public interface PemesananRepository extends JpaRepository<PemesananEntity, Long> {
    List<PemesananEntity> findAllByPenumpang_Id(Long penumpangId);
}
