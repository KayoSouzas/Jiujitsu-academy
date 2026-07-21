package com.dev.kayo.JiujitsuAcademy.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(
        name = "RegisterRequest",
        description = "Objeto utilizado para o cadastro de novos usuários na API."
)
public record RegisterRequest(

        @Email
        @NotBlank
        @Schema(
                description = "Endereço de e-mail do usuário. Deve ser um e-mail válido e único no sistema.",
                example = "kayo.souza@email.com",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String email,

        @NotBlank
        @Schema(
                description = "Senha que será utilizada para autenticação do usuário.",
                example = "MinhaSenha@123",
                format = "password",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String senha,

        @NotBlank
        @Schema(
                description = "Nome completo do usuário.",
                example = "Kayo Souza",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String nome

) {
}