package com.dev.kayo.JiujitsuAcademy.response;

import com.dev.kayo.JiujitsuAcademy.enums.Faixa;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "ProfessorResumoResponse",
        description = "Objeto resumido contendo as principais informações de um professor. Utilizado em respostas que não necessitam exibir todos os dados do professor."
)
public record ProfessorResumoResponse(

        @Schema(
                description = "Nome completo do professor.",
                example = "João Carlos da Silva"
        )
        String nome,

        @Schema(
                description = "Faixa atual do professor na modalidade de Jiu-Jitsu.",
                example = "PRETA",
                implementation = Faixa.class
        )
        Faixa faixaAtual

) {
}