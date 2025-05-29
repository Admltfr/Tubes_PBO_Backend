package com.example.tubespbo.tubespbo.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResetPasswordRequest {
    @NotBlank(message = "Email tidak boleh kosong")
    @Email(message = "Format email tidak valid")
    private String email;

    // @NotBlank(message = "Reset token tidak boleh kosong")
    // private String resetToken;

    @NotBlank(message = "password baru tidak boleh kosong")
    @Size(min = 8, max = 30, message = "Password harus berada diantara 8 sampai 20 karakter")
    private String newPassword;
}
