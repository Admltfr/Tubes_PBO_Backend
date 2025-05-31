package com.example.tubespbo.tubespbo.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class PemesananRequest {

    @NotNull(message = "ID penumpang tidak boleh kosong")
    private Long penumpangId;

    @NotNull(message = "ID jadwal tidak boleh kosong")
    private Long jadwalId;

    @NotBlank(message = "Kelas tidak boleh kosong")
    @Size(min = 3, max = 20, message = "Kelas harus antara 3 sampai 20 karakter")
    private String kelas;

    @NotNull(message = "Harga tidak boleh kosong")
    @Min(value = 0, message = "Harga harus bernilai positif")
    private Double harga;
}
