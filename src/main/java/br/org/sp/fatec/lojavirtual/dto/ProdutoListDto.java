package br.org.sp.fatec.lojavirtual.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoListDto {

    private Long id;

    private String nome;

    @JsonProperty("foto_url")
    private String fotoUrl;

    private BigDecimal preco;

    private Boolean promocao;
}
