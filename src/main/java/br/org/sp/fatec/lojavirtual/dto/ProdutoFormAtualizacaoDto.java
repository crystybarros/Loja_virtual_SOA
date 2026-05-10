package br.org.sp.fatec.lojavirtual.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProdutoFormAtualizacaoDto {

    @NotBlank(message = "Sku não pode ser em branco ou vazio")
    private String sku;

    @NotBlank(message = "Nome do produto não pode ser branco ou vazio")
    private String nome;

    private String descricao;

    @NotBlank(message = "unidade_medida não pode ser em branco ou nulo")
    @Size(min=2, max=2, message = "unidade_medida deve ter exatamente 2 caracteres")
    @JsonProperty("unidade_medida")
    private String unidadeMedida;

    @JsonProperty("foto_url")
    @Pattern(regexp = "^https?:\\/\\/[^\\s/$.?#].[^\\s]*$", message = "Deve ser um endereço de internet válido")
    private String fotoUrl;
}
