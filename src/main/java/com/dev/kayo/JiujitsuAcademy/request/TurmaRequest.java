package com.dev.kayo.JiujitsuAcademy.request;

import com.dev.kayo.JiujitsuAcademy.enums.DiaSemana;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalTime;

@Schema(
        name = "TurmaRequest",
        description = "Objeto utilizado para o cadastro e atualização de turmas da academia."
)
public record TurmaRequest(

        @Positive
        @Schema(
                description = "Identificador único da turma. Normalmente utilizado apenas em operações de atualização.",
                example = "1",
                minimum = "1"
        )
        Long id,

        @NotBlank
        @Schema(
                description = "Nome da turma.",
                example = "Jiu-Jitsu Adulto Iniciante",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String nome,

        @NotNull
        @Schema(
                description = "Dia da semana em que a turma será realizada.",
                example = "SEGUNDA",
                implementation = DiaSemana.class,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        DiaSemana diaSemana,

        @NotNull
        @Schema(
                description = "Horário de início da aula no formato HH:mm:ss.",
                example = "19:00:00",
                type = "string",
                format = "time",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        LocalTime horarioInicio,

        @NotNull
        @Schema(
                description = "Horário de término da aula no formato HH:mm:ss.",
                example = "20:30:00",
                type = "string",
                format = "time",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        LocalTime horarioFim,

        @Positive
        @NotNull
        @Schema(
                description = "Quantidade máxima de alunos permitidos na turma.",
                example = "30",
                minimum = "1",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Integer capacidadeMaxima,

        @NotNull
        @Schema(
                description = "Identificador único do professor responsável pela turma.",
                example = "5",
                minimum = "1",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Long professorId

) {
}