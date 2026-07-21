package com.dev.kayo.JiujitsuAcademy.response;

import com.dev.kayo.JiujitsuAcademy.enums.Faixa;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "AlunoResumoResponse",
        description = "Objeto resumido contendo as principais informações de um aluno. Utilizado em respostas que não necessitam exibir todos os dados do aluno."
)
public record AlunoResumoResponse(

        @Schema(
                description = "Nome completo do aluno.",
                example = "Carlos Henrique da Silva"
        )
        String nome,

        @Schema(
                description = "Faixa atual do aluno na modalidade de Jiu-Jitsu.",
                example = "AZUL",
                implementation = Faixa.class
        )
        Faixa faixaAtual

) {
}