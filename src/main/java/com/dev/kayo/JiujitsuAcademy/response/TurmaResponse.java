package com.dev.kayo.JiujitsuAcademy.response;

import com.dev.kayo.JiujitsuAcademy.entity.Aluno;
import com.dev.kayo.JiujitsuAcademy.entity.Professor;
import com.dev.kayo.JiujitsuAcademy.enums.DiaSemana;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import java.time.LocalTime;
import java.util.List;


@Builder
public record TurmaResponse(
        @NotNull @NotBlank String nome,
        @NotNull @Positive Long id,
        @NotNull LocalTime horarioInicio,
        @NotNull LocalTime horarioFim,
        @NotNull @Positive Integer capacidadeMaxima,
        @NotNull DiaSemana diaSemana,
        @NotNull ProfessorResumoResponse professor,
        List<AlunoResumoResponse> alunos
) {
}
