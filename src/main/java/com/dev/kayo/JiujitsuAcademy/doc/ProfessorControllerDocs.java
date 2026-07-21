package com.dev.kayo.JiujitsuAcademy.doc;

import com.dev.kayo.JiujitsuAcademy.request.ProfessorRequest;
import com.dev.kayo.JiujitsuAcademy.response.ProfessorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;
@Tag(
        name = "Professores",
        description = "Endpoints responsáveis pelo gerenciamento dos professores."
)
public interface ProfessorControllerDocs {

    @Operation(
            summary = "Listar professores",
            description = "Método responsável por retornar a lista completa de professores cadastrados na academia.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de professores retornada com sucesso.",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProfessorResponse.class)))
    )
    @ApiResponse(
            responseCode = "401",
            description = "Usuário não autenticado.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "403",
            description = "Usuário autenticado sem permissão para acessar o recurso.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "404",
            description = "Sem professores cadastrados.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro interno do servidor.",
            content = @Content()
    )
    ResponseEntity<List<ProfessorResponse>> findAll();


    @Operation(
            summary = "Buscar professor por ID",
            description = "Método responsável por buscar um professor específico através do seu identificador.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(
            responseCode = "200",
            description = "Professor encontrado com sucesso.",
            content = @Content(schema = @Schema(implementation = ProfessorResponse.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Professor não encontrado para o ID informado.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "401",
            description = "Usuário não autenticado.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "403",
            description = "Usuário autenticado sem permissão para acessar o recurso.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro interno do servidor.",
            content = @Content()
    )
    ResponseEntity<ProfessorResponse> findById(
            @Parameter(
                    description = "ID do professor.",
                    example = "1",
                    required = true
            )
            Long id
    );


    @Operation(
            summary = "Cadastrar professor",
            description = "Método responsável por realizar o cadastro de um novo professor na academia.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @RequestBody(
            description = "Dados necessários para o cadastro do professor.",
            required = true,
            content = @Content(schema = @Schema(implementation = ProfessorRequest.class))
    )
    @ApiResponse(
            responseCode = "201",
            description = "Professor cadastrado com sucesso.",
            content = @Content(schema = @Schema(implementation = ProfessorResponse.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Os dados enviados são inválidos.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "401",
            description = "Usuário não autenticado.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "403",
            description = "Usuário autenticado sem permissão para acessar o recurso.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro interno do servidor.",
            content = @Content()
    )
    ResponseEntity<ProfessorResponse> save(ProfessorRequest request);


    @Operation(
            summary = "Atualizar professor",
            description = "Método responsável por atualizar as informações de um professor já cadastrado através do seu ID.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @RequestBody(
            description = "Novos dados do professor.",
            required = true,
            content = @Content(schema = @Schema(implementation = ProfessorRequest.class))
    )
    @ApiResponse(
            responseCode = "200",
            description = "Professor atualizado com sucesso.",
            content = @Content(schema = @Schema(implementation = ProfessorResponse.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Os dados enviados são inválidos.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "404",
            description = "Professor não encontrado para atualização.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "401",
            description = "Usuário não autenticado.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "403",
            description = "Usuário autenticado sem permissão para acessar o recurso.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro interno do servidor.",
            content = @Content()
    )
    ResponseEntity<ProfessorResponse> update(
            @Parameter(
                    description = "ID do professor a ser atualizado.",
                    example = "1",
                    required = true
            )
            Long id,
            ProfessorRequest request
    );


    @Operation(
            summary = "Excluir professor",
            description = "Método responsável por remover um professor cadastrado através do seu ID.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(
            responseCode = "204",
            description = "Professor removido com sucesso.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "404",
            description = "Professor não encontrado para exclusão.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "401",
            description = "Usuário não autenticado.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "403",
            description = "Usuário autenticado sem permissão para acessar o recurso.",
            content = @Content()
    )
    @ApiResponse(
            responseCode = "500",
            description = "Erro interno do servidor.",
            content = @Content()
    )
    ResponseEntity<Void> delete(
            @Parameter(
                    description = "ID do professor a ser removido.",
                    example = "1",
                    required = true
            )
            Long id
    );
}