package com.example.tubespbo.tubespbo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tubespbo.tubespbo.model.response.KeretaResponse;
import com.example.tubespbo.tubespbo.service.KeretaService;

@RestController
@RequestMapping("/api/trains")
public class KeretaController {
    @Autowired
    private KeretaService keretaService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<KeretaResponse> getAllKereta() {
        return keretaService.getAllKereta();
    }

    // @GetMapping("/id")
    // @PreAuthorize("isAuthenticated()")
    // public KeretaResponse getCurrentKereta(String asal) {
    //     return keretaService.getKeretaByAsal(asal);
    // }
}