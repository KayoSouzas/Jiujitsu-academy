package com.dev.kayo.JiujitsuAcademy.request;

import com.dev.kayo.JiujitsuAcademy.enums.Faixa;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
@Schema(
        name = "ProfessorRequest",
        description = "Objeto utilizado para o cadastro e atualização de professores da academia."
)
public record ProfessorRequest(

        @NotBlank
        @Schema(
                description = "Nome completo do professor.",
                example = "João Carlos da Silva",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String nome,

        @Email
        @NotBlank
        @Schema(
                description = "Endereço de e-mail do professor. Deve ser um e-mail válido e único no sistema.",
                example = "joao.silva@email.com",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String email,

        @Positive
        @NotNull
        @Schema(
                description = "Idade do professor em anos. Deve ser um número inteiro maior que zero.",
                example = "35",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        int idade,

        @NotNull
        @Schema(
                description = "Faixa atual do professor na modalidade de Jiu-Jitsu.",
                example = "PRETA",
                implementation = Faixa.class,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Faixa faixaAtual

) {
}