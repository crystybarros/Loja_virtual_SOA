package br.org.sp.fatec.lojavirtual.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PedidoListDto {

    private Long id;

    private LocalDateTime data;

    @JsonProperty("numero_pedido")
    private Long numeroPedido;

    private BigDecimal total;

    private String situacao;


}
