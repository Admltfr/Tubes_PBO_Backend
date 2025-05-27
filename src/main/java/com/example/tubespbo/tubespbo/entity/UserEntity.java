package com.example.tubespbo.tubespbo.entity;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

// @Data
// @Entity 
// @NoArgsConstructor
// @AllArgsConstructor
// @Table(name="users")
// @Builder
// public class UserEntity {
//   @Id
//   @GeneratedValue(strategy=GenerationType.AUTO)
//   private String Id;

//   private String username;
  
//   private String password;

//   private String email;

//   // @OneToMany(mappedBy="userEntity",cascade=CascadeType.ALL)
//   // private List<JadwalEntity> riwayat;
  
// }

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@SuperBuilder
public abstract class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
}