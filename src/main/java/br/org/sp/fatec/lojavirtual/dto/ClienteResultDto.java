package br.org.sp.fatec.lojavirtual.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ClienteResultDto {

    @Schema(
            description = "id do cliente",
            example = "123"
    )
    private Long id;

    @Schema(
            description = "Nome do cliente",
            example = "José da Silva",
            minLength = 5, maxLength = 255
    )
    private String nome;

    @Schema(
            description = "CPF",
            example = "123456789-00",
            pattern = "\\d{9}-?\\d{3}"
    )
    private String cpf;

    @Schema(
            description = "Telefone",
            example = "(12)34567-8910"

    )
    private String telefone;

    private String email;
}
