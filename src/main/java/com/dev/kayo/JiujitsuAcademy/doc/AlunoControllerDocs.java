package com.dev.kayo.JiujitsuAcademy.doc;

import com.dev.kayo.JiujitsuAcademy.request.AlunoRequest;
import com.dev.kayo.JiujitsuAcademy.response.AlunoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@Tag(
        name = "Alunos",
        description = "Endpoints responsáveis pelo gerenciamento dos alunos da academia."
)
public interface AlunoControllerDocs {

    @Operation(
            summary = "Listar alunos",
            description = "Método responsável por retornar a lista completa de alunos cadastrados na academia.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de alunos retornada com sucesso.",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = AlunoResponse.class)))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Nenhum aluno foi encontrado.",
            content = @Content()
    )
    ResponseEntity<List<AlunoResponse>> findAll();


    @Operation(
            summary = "Buscar aluno por ID",
            description = "Método responsável por buscar um aluno específico através do seu identificador.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(
            responseCode = "200",
            description = "Aluno encontrado com sucesso.",
            content = @Content(schema = @Schema(implementation = AlunoResponse.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Aluno não encontrado para o ID informado.",
            content = @Content()
    )
    ResponseEntity<Optional<AlunoResponse>> findById(Long id);


    @Operation(
            summary = "Cadastrar aluno",
            description = "Método responsável por realizar o cadastro de um novo aluno na academia.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @RequestBody(
            description = "Dados necessários para o cadastro do aluno.",
            required = true,
            content = @Content(schema = @Schema(implementation = AlunoRequest.class))
    )
    @ApiResponse(
            responseCode = "201",
            description = "Aluno cadastrado com sucesso.",
            content = @Content(schema = @Schema(implementation = AlunoResponse.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Os dados enviados são inválidos.",
            content = @Content()
    )
    ResponseEntity<AlunoResponse> save(AlunoRequest request);


    @Operation(
            summary = "Atualizar aluno",
            description = "Método responsável por atualizar as informações de um aluno já cadastrado através do seu ID.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @RequestBody(
            description = "Novos dados do aluno.",
            required = true,
            content = @Content(schema = @Schema(implementation = AlunoRequest.class))
    )
    @ApiResponse(
            responseCode = "200",
            description = "Aluno atualizado com sucesso.",
            content = @Content(schema = @Schema(implementation = AlunoResponse.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Os dados enviados são inválidos.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "404",
            description = "Aluno não encontrado para atualização.",
            content = @Content()
    )
    ResponseEntity<AlunoResponse> update(Long id, AlunoRequest request);


    @Operation(
            summary = "Excluir aluno",
            description = "Método responsável por remover um aluno cadastrado através do seu ID.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(
            responseCode = "204",
            description = "Aluno removido com sucesso.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "404",
            description = "Aluno não encontrado para exclusão.",
            content = @Content()
    )
    ResponseEntity<Void> delete(Long id);

}