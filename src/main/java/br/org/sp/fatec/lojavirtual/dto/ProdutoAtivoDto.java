package br.org.sp.fatec.lojavirtual.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProdutoAtivoDto {
    @NotNull
    private Boolean ativo;
}
