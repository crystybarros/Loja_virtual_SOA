package br.org.sp.fatec.lojavirtual.controller;

import br.org.sp.fatec.lojavirtual.dto.ClienteFormAtualizacaoDto;
import br.org.sp.fatec.lojavirtual.dto.ClienteFormCadastroDto;
import br.org.sp.fatec.lojavirtual.dto.ClienteListDto;
import br.org.sp.fatec.lojavirtual.dto.ClienteResultDto;
import br.org.sp.fatec.lojavirtual.model.Cliente;
import br.org.sp.fatec.lojavirtual.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cliente")
@Tag(name="Clientes", description= "Apis para lidar com clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/")
    @Operation(description = "Listagem paginada de clientes")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Página de Clientes")
    })
    public ResponseEntity<Page<ClienteListDto>> listagem (Pageable paginacao) {
        var pagina = clienteService.listarPaginado(paginacao);
        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/{id}")
    @Operation(description = "Recupera um cliente especifico")
    @ApiResponses(value={
            @ApiResponse(
                    responseCode = "200",
                    description = "Dados do cliente",
                    content = @Content(
                            mediaType = "application/json",
                            schema =  @Schema(implementation = ClienteResultDto.class)
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "Cliente não existe",
                    content = @Content(
                            mediaType = "*/*"
                    )
            )
    })
    public ResponseEntity<ClienteResultDto> buscarPorId (@Parameter(description = "Id do cliente") @PathVariable Long id){
        var cliente = clienteService.buscaPorId(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/")
    @Operation(description = "Cadastra um cliente")
    @ApiResponses(value={
            @ApiResponse(
                    responseCode = "201",
                    description = "Dados do cliente",
                    headers = {
                        @Header(name="Location",
                                description = "Endereço(URI) do cliente cadastrado")
                    },
                    content = { @Content(
                            mediaType = "application/json",
                            schema =  @Schema(implementation = ClienteResultDto.class)
                    ) }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro de validação",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    public ResponseEntity<ClienteResultDto> cadastro (
            @Valid @RequestBody ClienteFormCadastroDto form,
            UriComponentsBuilder builder
    ){
        var cliente = clienteService.cadastrar(form);
        var location = builder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity
                .created(location)
                .body(cliente);
    }

    @PutMapping("/{id}")
    @Operation(description = "Atualizar cliente")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cliente atualizado com sucesso"),

            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente não encontrado",
                    content = @Content(
                            mediaType = "*/*"
                    )
            )

    })
    public ResponseEntity<ClienteResultDto> atualizar (
            @Valid @RequestBody ClienteFormAtualizacaoDto form,
            @PathVariable Long id
    ){
        var clienteAtualizado = clienteService.atualizar(id, form);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Remover cliente")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Cliente removido com sucesso"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente não encontrado")
    })
    public ResponseEntity<?> remover (@PathVariable Long id){
        clienteService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
