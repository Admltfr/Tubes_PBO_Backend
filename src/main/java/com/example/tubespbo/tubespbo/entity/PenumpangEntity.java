package com.example.tubespbo.tubespbo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("PENUMPANG")
@SuperBuilder
public class PenumpangEntity extends UserEntity {
    private String nomorTelepon;
}
