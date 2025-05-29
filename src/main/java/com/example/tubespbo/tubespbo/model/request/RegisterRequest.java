package com.example.tubespbo.tubespbo.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    @NotBlank(message = "Username tidak boleh kosong")
    @Size(min = 3, max = 20, message = "Username harus berada diantara 3 sampai 20 karakter")
    private String username;

    @NotBlank(message = "Password tidak boleh kosong")
    @Size(min = 8, max = 30, message = "Password harus berada diantara 8 sampai 20 karakter")
    private String password;

    @NotBlank(message = "Email tidak boleh kosong")
    @Email(message = "Format email tidak valid")
    private String email;

    @NotBlank(message = "Role tidak boleh kosong")
    @Pattern(regexp = "ADMIN|PENUMPANG", flags = Pattern.Flag.CASE_INSENSITIVE, 
         message = "Role hanya boleh 'ADMIN' atau 'PENUMPANG.")
    private String role;

    @NotBlank(message = "nomor telepon tidak boleh kosong")
    private String nomorTelepon;
}

