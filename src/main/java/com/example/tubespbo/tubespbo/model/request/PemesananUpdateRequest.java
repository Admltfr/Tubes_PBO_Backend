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
public class PemesananUpdateRequest {
    private Long id;

    @NotNull(message = "ID penumpang tidak boleh kosong")
    private Long penumpangId;

    @NotNull(message = "ID jadwal tidak boleh kosong")
    private Long jadwalId;
}
