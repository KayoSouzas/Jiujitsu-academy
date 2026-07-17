package com.dev.kayo.JiujitsuAcademy.request;

import com.dev.kayo.JiujitsuAcademy.entity.Professor;
import com.dev.kayo.JiujitsuAcademy.enums.DiaSemana;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalTime;

public record TurmaRequest(
        @Positive Long id,
       @NotNull @NotBlank String nome,
       @NotNull DiaSemana diaSemana,
       @NotNull LocalTime horarioInicio,
       @NotNull LocalTime horarioFim,
       @NotNull @Positive Integer capacidadeMaxima,
       @NotNull Long professorId

) {
}
