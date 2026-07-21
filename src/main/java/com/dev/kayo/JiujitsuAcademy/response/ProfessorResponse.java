package com.dev.kayo.JiujitsuAcademy.response;

import com.dev.kayo.JiujitsuAcademy.enums.Faixa;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(
        name = "ProfessorResponse",
        description = "Objeto retornado pela API contendo as informações de um professor."
)
public record ProfessorResponse(

        @Schema(
                description = "Identificador único do professor.",
                example = "5"
        )
        Long id,

        @Schema(
                description = "Nome completo do professor.",
                example = "João Carlos da Silva"
        )
        String nome,

        @Schema(
                description = "Endereço de e-mail do professor.",
                example = "joao.silva@email.com"
        )
        String email,

        @Schema(
                description = "Idade do professor em anos.",
                example = "35",
                minimum = "1"
        )
        int idade,

        @Schema(
                description = "Faixa atual do professor na modalidade de Jiu-Jitsu.",
                example = "PRETA",
                implementation = Faixa.class
        )
        Faixa faixaAtual

) {
}