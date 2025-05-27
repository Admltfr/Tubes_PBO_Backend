package com.example.tubespbo.tubespbo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("ADMIN")
@SuperBuilder
public class AdminEntity extends UserEntity {
    private String sesuatu;
}
