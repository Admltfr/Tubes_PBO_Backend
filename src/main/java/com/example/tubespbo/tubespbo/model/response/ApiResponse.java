package com.example.tubespbo.tubespbo.model.response;

import org.springframework.http.HttpStatus;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private HttpStatus status;
    private boolean success;
    private String message;
    private T data;
}