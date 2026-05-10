package br.org.sp.fatec.lojavirtual.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ClienteFormCadastroDto {

    @NotBlank(message = "Nome do cliente não pode ser vazio")
    @Size(min = 5, max=255, message = "O Nome deve ter no minimo 5 caracteres e no máximo 255")
    @Schema(description = "Nome do cliente",
            example = "João da Silva",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String nome;

    @Pattern(regexp="(\\d{3}\\.?){3}-?\\d{2}", message = "Formato do CPF inválido")
    @NotNull(message = "CPF deve ser informado")
    private String cpf;

    @Pattern(regexp= "\\(?\\d{2}\\)?\\s?\\d{4,5}(-|\\s)?\\d{4}", message = "Formato de telefone inválido")
    private String telefone;

    @Email(message = "Deve ser informado um email válido")
    @Size(max = 150, message = "E-mail deve ter até 150 caracteres")
    @NotNull
    private String email;
}
