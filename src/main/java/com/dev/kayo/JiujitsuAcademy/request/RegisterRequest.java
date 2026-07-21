package com.dev.kayo.JiujitsuAcademy.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
                               @Email @NotBlank String email,
                               @NotBlank String senha,
                               @NotBlank String nome
) {
}
