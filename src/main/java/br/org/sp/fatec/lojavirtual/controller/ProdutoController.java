package br.org.sp.fatec.lojavirtual.controller;

import br.org.sp.fatec.lojavirtual.dto.*;
import br.org.sp.fatec.lojavirtual.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/produto")
@Tag(name="Produtos", description= "Apis para lidar com produtos")

public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @GetMapping("/")
    @Operation(description = "Recupera um produto especifico")
    @ApiResponses(value={
            @ApiResponse(
                    responseCode = "200",
                    description = "Dados do produto",
                    content = @Content(
                            mediaType = "application/json",
                            schema =  @Schema(implementation = ProdutoResultDto.class)
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "Produto não existe",
                    content = @Content(
                            mediaType = "*/*"
                    )
            )
    })
    public ResponseEntity<Page<ProdutoListDto>> listagemComFiltro(
            Pageable paginacao,
            @RequestParam(required = false) String filtro
    ) {
        var paginaProdutos = produtoService.getProdutos(paginacao, filtro);
        return ResponseEntity.ok(paginaProdutos);
    }

    @GetMapping("/{id}")
    @Operation(description = "Recupera um produto especifico")
    @ApiResponses(value={
            @ApiResponse(
                    responseCode = "200",
                    description = "Dados do produto",
                    content = @Content(
                            mediaType = "application/json",
                            schema =  @Schema(implementation = ProdutoResultDto.class)
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "Produto não existe",
                    content = @Content(
                            mediaType = "*/*"
                    )
            )
    })
    public ResponseEntity<?> procurarPorId(@PathVariable Long id){
        var produto = produtoService.getById(id);
        return ResponseEntity.ok(produto);

    }

    @PostMapping("/")
    @Operation(description = "Cadastra um produto")
    @ApiResponses(value={
            @ApiResponse(
                    responseCode = "201",
                    description = "Dados do produto",
                    headers = {
                            @Header(name="Location",
                                    description = "Endereço(URI) do produto cadastrado")
                    },
                    content = { @Content(
                            mediaType = "application/json",
                            schema =  @Schema(implementation = ProdutoResultDto.class)
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
    public ResponseEntity<ProdutoResultDto> criarProduto(@RequestBody @Valid ProdutoFormCadastroDto novo,
                                                         UriComponentsBuilder builder) {
        var produto = produtoService.criarProduto(novo);
        var uri = builder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable Long id,
                                              @RequestBody @Valid ProdutoFormAtualizacaoDto modificado) {
        var produto = produtoService.atualizarProduto(id, modificado);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> apagarProduto(@PathVariable Long id) {
        produtoService.apagarProduto(id);
        return ResponseEntity.noContent().build();

    }

    @PatchMapping("/{id}/preco")
    public ResponseEntity<?> atualizarPreco(
            @PathVariable Long id,
            @Valid @RequestBody PrecoFormDto preco
    ) {
        var produto = produtoService.atualizarPreco(id, preco);
        return ResponseEntity.ok(produto);
    }

    @PatchMapping("/{id}/ativo")
    public ResponseEntity<?> atualizarAtivo(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoAtivoDto ativo
    ) {
        var produto = produtoService.atualizarAtivo(id, ativo);
        return ResponseEntity.ok(produto);
    }


}
