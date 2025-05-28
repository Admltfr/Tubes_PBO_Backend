package com.example.tubespbo.tubespbo.exception;

//ini buat throw meesagenya

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
