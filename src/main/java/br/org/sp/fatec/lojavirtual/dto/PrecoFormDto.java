package br.org.sp.fatec.lojavirtual.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PrecoFormDto {

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value="0.01", message = "Preço não pode ser negativo ou gratuito")
    private BigDecimal valor;

    private Boolean promocao;
}
