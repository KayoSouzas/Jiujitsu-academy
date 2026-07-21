package com.dev.kayo.JiujitsuAcademy.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(
        name = "LoginRequest",
        description = "Objeto utilizado para autenticação de usuários na API."
)
public record LoginRequest(

        @Email
        @NotBlank
        @Schema(
                description = "Endereço de e-mail cadastrado do usuário.",
                example = "kayo.souza@email.com",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String email,

        @NotBlank
        @Schema(
                description = "Senha do usuário utilizada para autenticação.",
                example = "MinhaSenha@123",
                requiredMode = Schema.RequiredMode.REQUIRED,
                format = "password"
        )
        String senha

) {
}