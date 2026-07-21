package com.dev.kayo.JiujitsuAcademy.request;

import com.dev.kayo.JiujitsuAcademy.enums.Faixa;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(
        name = "AlunoRequest",
        description = "Objeto utilizado para o cadastro e atualização de alunos da academia."
)
public record AlunoRequest(

        @NotBlank
        @Schema(
                description = "Nome completo do aluno.",
                example = "Carlos Henrique da Silva",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String nome,

        @Email
        @NotBlank
        @Schema(
                description = "Endereço de e-mail do aluno. Deve ser um e-mail válido e único no sistema.",
                example = "carlos.silva@email.com",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String email,

        @Positive
        @NotNull
        @Schema(
                description = "Idade do aluno em anos. Deve ser um número inteiro maior que zero.",
                example = "25",
                minimum = "1",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        int idade,

        @NotNull
        @Schema(
                description = "Faixa atual do aluno dentro da modalidade de Jiu-Jitsu.",
                example = "AZUL",
                implementation = Faixa.class,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Faixa faixaAtual

) {
}