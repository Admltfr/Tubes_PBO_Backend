package com.example.tubespbo.tubespbo.model.request;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JadwalRequest {

    @NotNull(message = "ID penumpang tidak boleh kosong")
    private Long keretaId;

    // Formatnya pakek ISO-8601 yak
    @NotNull(message = "Waktu keberangkatan tidak boleh kosong")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date waktuKeberangkatan;

    @NotNull(message = "Rute tidak boleh kosong")
    private List<String> rute;

    @NotNull(message = "Kelas tidak boleh kosong")
    @Size(min = 3, max = 20, message = "Kelas harus antara 3 sampai 20 karakter")
    private String kelas;

    @NotNull(message = "Harga tidak boleh kosong")
    @Min(value = 0, message = "Harga harus bernilai positif")
    private Double harga;
}
