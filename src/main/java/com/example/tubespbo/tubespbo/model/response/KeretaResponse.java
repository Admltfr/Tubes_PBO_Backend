package com.example.tubespbo.tubespbo.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KeretaResponse {
    private Long id;

    private String asal;

    private String tujuan;
}
