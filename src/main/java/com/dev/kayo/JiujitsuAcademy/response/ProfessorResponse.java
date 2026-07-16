package com.dev.kayo.JiujitsuAcademy.response;

import com.dev.kayo.JiujitsuAcademy.enums.Faixa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record ProfessorResponse(
        @NotNull @Positive Long id,
        @NotNull @NotBlank String nome,
        @NotNull @Email String email,
        @NotNull @Positive int idade,
        @NotNull Faixa faixaAtual

) {
}
