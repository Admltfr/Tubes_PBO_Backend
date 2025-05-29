package com.example.tubespbo.tubespbo.mapper;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.example.tubespbo.tubespbo.model.response.ApiResponse;

//wajib component karena nanti spring membuat bean untuk autowired pas dipanggil di controller
//yang dimana bean ini kurang lebih seperti menghidupkan variabelnya hingga bisa diinjeksi pakai autwired di class lain
//component, service dan repository adalah ketiga hal yang bakal bisa diinjeksi pake autowired
@Component
public class ApiResponseMapper {
    public <T> ApiResponse<T> ToApiResponse(HttpStatus status, String message, T data) {
        return ApiResponse.<T>builder()
                .status(HttpStatus.OK)
                .message(message)
                .data(data)
                .build();
    }
}
