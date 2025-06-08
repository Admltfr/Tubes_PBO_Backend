package com.example.tubespbo.tubespbo.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeretaUpdateRequest {
    private Long id;
    
    @NotBlank(message = "Asal tidak boleh kosong")
    private String asal;

    @NotBlank(message = "Tujuan tidak boleh kosong")
    private String tujuan;
}
