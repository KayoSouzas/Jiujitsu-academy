package com.dev.kayo.JiujitsuAcademy.doc;

import com.dev.kayo.JiujitsuAcademy.request.LoginRequest;
import com.dev.kayo.JiujitsuAcademy.request.RegisterRequest;
import com.dev.kayo.JiujitsuAcademy.response.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(
        name = "Autenticação",
        description = "Endpoints responsáveis pelo cadastro e autenticação de usuários."
)
public interface AuthControllerDocs {

    @Operation(
            summary = "Cadastrar usuário",
            description = "Método responsável por realizar o cadastro de um novo usuário no sistema. Após o cadastro, o usuário poderá autenticar-se utilizando suas credenciais."
    )
    @RequestBody(
            description = "Dados necessários para o cadastro do usuário.",
            required = true,
            content = @Content(schema = @Schema(implementation = RegisterRequest.class))
    )
    @ApiResponse(
            responseCode = "201",
            description = "Usuário cadastrado com sucesso."
    )
    @ApiResponse(
            responseCode = "400",
            description = "Os dados informados são inválidos.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "409",
            description = "Já existe um usuário cadastrado com os dados informados.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro interno do servidor.",
            content = @Content()
    )
    ResponseEntity<Void> register(RegisterRequest request);


    @Operation(
            summary = "Autenticar usuário",
            description = "Método responsável por autenticar um usuário através de suas credenciais e retornar um token JWT para acesso aos recursos protegidos da API."
    )
    @RequestBody(
            description = "Credenciais de autenticação do usuário.",
            required = true,
            content = @Content(schema = @Schema(implementation = LoginRequest.class))
    )
    @ApiResponse(
            responseCode = "200",
            description = "Autenticação realizada com sucesso.",
            content = @Content(schema = @Schema(implementation = LoginResponse.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Os dados informados são inválidos.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "401",
            description = "Usuário ou senha inválidos.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro interno do servidor.",
            content = @Content()
    )
    ResponseEntity<LoginResponse> login(LoginRequest request);

}