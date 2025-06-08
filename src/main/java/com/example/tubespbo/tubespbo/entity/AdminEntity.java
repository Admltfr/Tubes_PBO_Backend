package com.example.tubespbo.tubespbo.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("ADMIN")
@SuperBuilder
public class AdminEntity extends UserEntity {

}
