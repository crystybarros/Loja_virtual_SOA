package br.org.sp.fatec.lojavirtual.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoFormCadastroDto extends ProdutoFormAtualizacaoDto {

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value="0.01", message = "Preço não pode ser negativo ou gratuito")
    private BigDecimal preco;

    @NotNull(message = "Estoque Inicial")
    @DecimalMin(value="0.01", message = "Estoque não pode ser negativo ou zero")
    @JsonProperty("estoque_inicial")
    private BigDecimal estoqueInicial;

}
