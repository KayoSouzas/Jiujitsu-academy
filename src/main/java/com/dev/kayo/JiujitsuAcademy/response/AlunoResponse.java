package com.dev.kayo.JiujitsuAcademy.response;

import com.dev.kayo.JiujitsuAcademy.enums.Faixa;
import com.dev.kayo.JiujitsuAcademy.enums.Matricula;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(
        name = "AlunoResponse",
        description = "Objeto retornado pela API contendo as informações de um aluno."
)
public record AlunoResponse(

        @Schema(
                description = "Identificador único do aluno.",
                example = "1"
        )
        Long id,

        @Schema(
                description = "Nome completo do aluno.",
                example = "Carlos Henrique da Silva"
        )
        String nome,

        @Schema(
                description = "Idade do aluno em anos.",
                example = "25",
                minimum = "1"
        )
        int idade,

        @Schema(
                description = "Faixa atual do aluno na modalidade de Jiu-Jitsu.",
                example = "AZUL",
                implementation = Faixa.class
        )
        Faixa faixaAtual,

        @Schema(
                description = "Endereço de e-mail do aluno.",
                example = "carlos.silva@email.com"
        )
        String email,

        @Schema(
                description = "Situação da matrícula do aluno.",
                example = "ATIVA",
                implementation = Matricula.class
        )
        Matricula matricula

) {
}