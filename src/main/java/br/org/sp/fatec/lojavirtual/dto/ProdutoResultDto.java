package br.org.sp.fatec.lojavirtual.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoResultDto {

    @Schema(
            description = "id do Produto",
            example = "123")
    private Long id;

    @Schema(
            description = "SKU",
            example = "IP16-PR-128",
            minLength = 6, maxLength = 12)
    private String sku;

    @Schema(
            description = "Produto",
            example = "iPhone 16",
            minLength = 5, maxLength = 255)
    private String nome;

    @Schema(
            description = "Descrição",
            example = "Preto, 128GB")
    private String descricao;


    @Schema(
            description = "Unidade de medida",
            example = "unidade")
    @JsonProperty("unidade_medida")
    private String unidadeMedida;

    private Boolean ativo;

    @JsonProperty("foto_url")
    private String fotoUrl;

    @Schema(
            description = "Preço",
            example = "R$5.000,00"
    )
    private BigDecimal preco;

    private Boolean promocao;

    @Schema(
            description = "Estoque",
            example = "20"
    )
    private BigDecimal estoque;
}
