package br.org.sp.fatec.lojavirtual.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoResultDto {

    private Long id;

    private LocalDateTime data;

    @JsonProperty("numero_pedido")
    private Long numeroPedido;

    private BigDecimal total;

    /*
        No futuro o interessante seria não trazer os dados do cliente,
        mas sim do endereço, está ao contrario para ter exemplo
     */

    private ClienteResultDto cliente;

    @JsonProperty("endereco_id")
    private Long enderecoId;

    private String situacao;

    private List<PedidoItemResultDto> itens;

}
