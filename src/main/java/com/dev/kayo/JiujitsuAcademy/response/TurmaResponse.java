package com.dev.kayo.JiujitsuAcademy.response;

import com.dev.kayo.JiujitsuAcademy.enums.DiaSemana;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalTime;
import java.util.List;

@Builder
@Schema(
        name = "TurmaResponse",
        description = "Objeto retornado pela API contendo as informações de uma turma, seu professor responsável e os alunos matriculados."
)
public record TurmaResponse(

        @Schema(
                description = "Nome da turma.",
                example = "Jiu-Jitsu Adulto Iniciante"
        )
        String nome,

        @Schema(
                description = "Identificador único da turma.",
                example = "1"
        )
        Long id,

        @Schema(
                description = "Horário de início da aula no formato HH:mm:ss.",
                example = "19:00:00",
                type = "string",
                format = "time"
        )
        LocalTime horarioInicio,

        @Schema(
                description = "Horário de término da aula no formato HH:mm:ss.",
                example = "20:30:00",
                type = "string",
                format = "time"
        )
        LocalTime horarioFim,

        @Schema(
                description = "Quantidade máxima de alunos permitidos na turma.",
                example = "30",
                minimum = "1"
        )
        Integer capacidadeMaxima,

        @Schema(
                description = "Dia da semana em que a turma é realizada.",
                example = "SEGUNDA",
                implementation = DiaSemana.class
        )
        DiaSemana diaSemana,

        @Schema(
                description = "Professor responsável pela turma."
        )
        ProfessorResumoResponse professor,

        @ArraySchema(
                schema = @Schema(
                        implementation = AlunoResumoResponse.class,
                        description = "Lista de alunos matriculados na turma."
                )
        )
        List<AlunoResumoResponse> alunos

) {
}