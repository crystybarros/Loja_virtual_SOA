package br.org.sp.fatec.lojavirtual.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PedidoItemFormDto {

    @NotNull(message = "Id do produto não pode ser nulo")
    @JsonProperty("produto_id")
    private Long produtoId;

    @NotNull(message = "Quantidade não pode ser nula")
    @DecimalMin(value = "0.01", message = "Quantidade deve ser positiva e diferente de zero.")
    private BigDecimal quantidade;
}
