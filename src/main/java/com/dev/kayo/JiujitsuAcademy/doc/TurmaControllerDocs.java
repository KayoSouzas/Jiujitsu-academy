package com.dev.kayo.JiujitsuAcademy.doc;

import com.dev.kayo.JiujitsuAcademy.request.TurmaRequest;
import com.dev.kayo.JiujitsuAcademy.response.TurmaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@Tag(
        name = "Turmas",
        description = "Endpoints responsáveis pelo gerenciamento das turmas e matrícula de alunos."
)
public interface TurmaControllerDocs {

    @Operation(
            summary = "Cadastrar turma",
            description = "Método responsável por cadastrar uma nova turma e vinculá-la ao professor informado na requisição.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(
            responseCode = "201",
            description = "Turma cadastrada com sucesso.",
            content = @Content(schema = @Schema(implementation = TurmaResponse.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Os dados informados são inválidos.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "401",
            description = "Usuário não autenticado.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "403",
            description = "Usuário sem permissão para realizar esta operação.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "404",
            description = "Professor informado não foi encontrado.",
            content = @Content()
    )
    ResponseEntity<TurmaResponse> save(TurmaRequest request);


    @Operation(
            summary = "Matricular aluno em uma turma",
            description = "Método responsável por matricular um aluno em uma turma existente através dos identificadores da turma e do aluno.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(
            responseCode = "201",
            description = "Aluno matriculado na turma com sucesso.",
            content = @Content(schema = @Schema(implementation = TurmaResponse.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Não foi possível realizar a matrícula devido a dados inválidos.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "404",
            description = "Turma ou aluno não encontrados.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "401",
            description = "Usuário não autenticado.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "403",
            description = "Usuário sem permissão para realizar esta operação.",
            content = @Content()
    )
    ResponseEntity<TurmaResponse> matricular(

            @Parameter(
                    description = "Identificador único da turma.",
                    example = "1",
                    required = true
            )
            Long turmaID,

            @Parameter(
                    description = "Identificador único do aluno.",
                    example = "10",
                    required = true
            )
            Long alunoID
    );


    @Operation(
            summary = "Listar turmas",
            description = "Método responsável por retornar todas as turmas cadastradas na academia.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de turmas retornada com sucesso.",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = TurmaResponse.class)))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Nenhuma turma foi encontrada.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "401",
            description = "Usuário não autenticado.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "403",
            description = "Usuário sem permissão para realizar esta operação.",
            content = @Content()
    )
    ResponseEntity<List<TurmaResponse>> findAll();


    @Operation(
            summary = "Buscar turma por ID",
            description = "Método responsável por buscar uma turma através do seu identificador.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(
            responseCode = "200",
            description = "Turma encontrada com sucesso.",
            content = @Content(schema = @Schema(implementation = TurmaResponse.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Turma não encontrada.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "401",
            description = "Usuário não autenticado.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "403",
            description = "Usuário sem permissão para realizar esta operação.",
            content = @Content()
    )
    ResponseEntity<Optional<TurmaResponse>> findById(

            @Parameter(
                    description = "Identificador único da turma.",
                    example = "1",
                    required = true
            )
            Long id
    );


    @Operation(
            summary = "Atualizar turma",
            description = "Método responsável por atualizar os dados de uma turma previamente cadastrada.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(
            responseCode = "200",
            description = "Turma atualizada com sucesso.",
            content = @Content(schema = @Schema(implementation = TurmaResponse.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Os dados informados são inválidos.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "404",
            description = "Turma ou professor não encontrados.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "401",
            description = "Usuário não autenticado.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "403",
            description = "Usuário sem permissão para realizar esta operação.",
            content = @Content()
    )
    ResponseEntity<TurmaResponse> update(

            @Parameter(
                    description = "Identificador único da turma.",
                    example = "1",
                    required = true
            )
            Long id,

            TurmaRequest request
    );


    @Operation(
            summary = "Excluir turma",
            description = "Método responsável por remover uma turma cadastrada através do seu identificador.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(
            responseCode = "204",
            description = "Turma removida com sucesso.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "404",
            description = "Turma não encontrada.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "401",
            description = "Usuário não autenticado.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "403",
            description = "Usuário sem permissão para realizar esta operação.",
            content = @Content()
    )
    ResponseEntity<Void> delete(

            @Parameter(
                    description = "Identificador único da turma.",
                    example = "1",
                    required = true
            )
            Long id
    );

}