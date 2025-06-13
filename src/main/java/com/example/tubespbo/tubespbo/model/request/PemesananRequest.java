package com.example.tubespbo.tubespbo.model.request;

import jakarta.validation.constraints.NotNull;
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


}
