package br.org.sp.fatec.lojavirtual.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class PedidoFormDto {

    @NotNull(message = "Id do cliente é obrigatório")
    @JsonProperty("cliente_id")
    private Long clienteId;

    @NotNull(message = "Id do endereço é obrigatório")
    @JsonProperty("endereco_entrega_id")
    private Long enderecoEntregaId;

    @NotNull(message = "A lista de itens não pode ser nula")
    @Size(min = 1, message = "Deve ter pelo menos um item")
    private List<PedidoItemFormDto> itens;

}
