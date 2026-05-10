package br.org.sp.fatec.lojavirtual.controller;

import br.org.sp.fatec.lojavirtual.dto.EnderecoFormDto;
import br.org.sp.fatec.lojavirtual.model.Endereco;
import br.org.sp.fatec.lojavirtual.service.EnderecoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
@Tag(name = "Endereços", description = "Apis para lidar com endereços")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    @Operation(description = "Listagem de endereços")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de endereços"
            )
    })
    public List<Endereco> listar() {

        return enderecoService.listar();
    }

    @GetMapping("/{id}")
    @Operation(description = "Recupera um endereço específico")
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Dados do endereço",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = Endereco.class
                            )
                    )
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "Endereço não encontrado",
                    content = @Content(
                            mediaType = "*/*"
                    )
            )
    })
    public Endereco buscarPorId(

            @Parameter(description = "Id do endereço")
            @PathVariable Long id

    ) {

        return enderecoService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Cadastrar endereço")
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "201",
                    description = "Endereço cadastrado com sucesso",
                    headers = {
                            @Header(
                                    name = "Location",
                                    description = "URI do endereço criado"
                            )
                    },
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = Endereco.class
                            )
                    )
            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "Erro de validação",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ProblemDetail.class
                            )
                    )
            )
    })
    public Endereco cadastrar(

            @RequestBody @Valid EnderecoFormDto dto

    ) {

        return enderecoService.cadastrar(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Remover endereço")
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "204",
                    description = "Endereço removido com sucesso"
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "Endereço não encontrado"
            )
    })
    public void deletar(

            @Parameter(description = "Id do endereço")
            @PathVariable Long id

    ) {

        enderecoService.deletar(id);
    }
}