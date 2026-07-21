package com.dev.kayo.JiujitsuAcademy.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "LoginResponse",
        description = "Objeto retornado após uma autenticação realizada com sucesso. Contém as informações básicas do usuário autenticado e o token JWT utilizado para acessar os recursos protegidos da API."
)
public record LoginResponse(

        @Schema(
                description = "Nome do usuário autenticado.",
                example = "Kayo Souza"
        )
        String nome,

        @Schema(
                description = "Token JWT que deve ser enviado no cabeçalho Authorization das requisições protegidas utilizando o formato 'Bearer {token}'.",
                example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXlvQGVtYWlsLmNvbSIsImlhdCI6MTcyMTU3NTYwMCwiZXhwIjoxNzIxNTc5MjAwfQ.X8Wn9bQYw4cK5q2M3rN7sF1vT6yZ8pL4dE9gH2jK5Lm"
        )
        String token

) {
}