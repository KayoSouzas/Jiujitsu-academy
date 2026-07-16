package com.dev.kayo.JiujitsuAcademy.request;

import com.dev.kayo.JiujitsuAcademy.enums.Faixa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record ProfessorRequest(
        @NotNull @NotBlank String nome,
        @NotBlank @Email String email,
        @NotNull @Positive int idade,
        @NotNull Faixa faixaAtual
) {
}
