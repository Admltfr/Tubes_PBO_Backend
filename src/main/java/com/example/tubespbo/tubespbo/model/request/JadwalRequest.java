package com.example.tubespbo.tubespbo.model.request;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Tanggal tidak boleh kosong")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    private Date tanggal;

    // Formatnya pakek ISO-8601 yak
    @NotNull(message = "Waktu keberangkatan tidak boleh kosong")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    private Date waktuKeberangkatan;

    @NotNull(message = "Rute tidak boleh kosong")
    private List<String> rute;

}
