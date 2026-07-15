package com.dev.kayo.JiujitsuAcademy.request;

import com.dev.kayo.JiujitsuAcademy.enums.Faixa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AlunoRequest(
        @NotNull @NotBlank String nome,
        @Email @NotBlank String email,
        @Positive @NotNull int idade,
        @NotNull Faixa faixa) {
}
