package com.dev.kayo.JiujitsuAcademy.response;

import com.dev.kayo.JiujitsuAcademy.entity.Professor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalTime;

public record TurmaResponse(
        @NotNull @NotBlank String nome,
        @NotNull @Positive Long id,
        @NotNull LocalTime horrarioInicio,
        @NotNull LocalTime horarioFim,
        @NotNull @Positive Integer capacidadeMaxima,
        @NotNull Professor professor
) {
}
