package com.example.tubespbo.tubespbo.controller;

import java.util.List;

// import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.example.tubespbo.tubespbo.entity.KeretaEntity;
import com.example.tubespbo.tubespbo.exception.ApiException;
import com.example.tubespbo.tubespbo.model.request.KeretaRequest;
import com.example.tubespbo.tubespbo.model.response.ApiResponse;
import com.example.tubespbo.tubespbo.model.response.KeretaResponse;
import com.example.tubespbo.tubespbo.mapper.ApiResponseMapper;
import com.example.tubespbo.tubespbo.service.KeretaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/trains")
public class KeretaController {

    @Autowired
    private KeretaService keretaService;

    @Autowired
    private ApiResponseMapper responseBuilder;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse<List<KeretaResponse>>> getAllUsers() {
        try {
            List<KeretaResponse> trains = keretaService.getAllKereta();
            ApiResponse<List<KeretaResponse>> response = responseBuilder.ToApiResponse(HttpStatus.OK, "Berhasil mengambil data semua kereta", trains);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            throw new ApiException("Gagal mengambil data semua user");
        }
    }

    @GetMapping("/id")
    @PreAuthorize("isAuthenticated()")
    public KeretaResponse getCurrentKereta(String asal) {
        return keretaService.getKeretaByAsal(asal);
    }

    @PostMapping("add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse<KeretaResponse>> createKereta(@RequestBody KeretaRequest kereta) {
        try {
            KeretaResponse result = keretaService.createKereta(kereta);
            ApiResponse<KeretaResponse> response = responseBuilder.ToApiResponse(HttpStatus.OK, "Berhasil menambahkan data kereta", result);
            return ResponseEntity.ok(response);
        } catch (ApiException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiException("Terjadi kesalahan saat memasukkan data kereta");
        }
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse<KeretaResponse>> updateKereta(@PathVariable Long id, @RequestBody KeretaRequest kereta) {
        try {
            kereta.setId(id); // set ID from path
            KeretaResponse result = keretaService.updateKereta(kereta);
            ApiResponse<KeretaResponse> response = responseBuilder.ToApiResponse(HttpStatus.OK, "Berhasil mengupdate data kereta", result);
            return ResponseEntity.ok(response);
        } catch (ApiException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApiException("Terjadi kesalahan saat mengubah data kereta");
        }
    }
    
    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse<KeretaResponse>> deleteKereta(@PathVariable Long id) {
        try {
            KeretaResponse result = keretaService.deleteKereta(id);
            ApiResponse<KeretaResponse> response = responseBuilder.ToApiResponse(HttpStatus.OK, "Berhasil menghapus data kereta", result);
            return ResponseEntity.ok(response);
        } catch (ApiException ex) {
            throw ex; 
        } catch (Exception ex) {
            throw new ApiException("Terjadi kesalahan saat menghapus data kereta");
        }
    }
}
