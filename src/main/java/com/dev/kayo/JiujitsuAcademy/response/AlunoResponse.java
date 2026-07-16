package com.dev.kayo.JiujitsuAcademy.response;

import com.dev.kayo.JiujitsuAcademy.enums.Faixa;
import com.dev.kayo.JiujitsuAcademy.enums.Matricula;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record AlunoResponse(
        @NotNull @NotBlank String nome,
        @NotNull @Positive int idade,
        @NotNull Faixa faixaAtual,
        @Positive @NotNull Long id,
        @NotNull @Email String email,
        @NotNull Matricula matricula
) {
}
