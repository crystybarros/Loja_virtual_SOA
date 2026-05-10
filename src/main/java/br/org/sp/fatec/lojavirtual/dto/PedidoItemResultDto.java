package br.org.sp.fatec.lojavirtual.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PedidoItemResultDto {

    private ProdutoListDto produto;

    private BigDecimal quantidade;

    private BigDecimal preco;

    private BigDecimal total;

}
