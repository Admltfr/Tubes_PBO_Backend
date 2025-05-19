package com.example.tubespbo.tubespbo.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity 
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
@Builder
public class UserEntity {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private String Id;

  private String username;
  
  private String password;

  private String email;

  @OneToMany(mappedBy="userEntity",cascade=CascadeType.ALL)
  private List<JadwalEntity> riwayat;
  
}