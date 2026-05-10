package br.org.sp.fatec.lojavirtual.dto;

import lombok.Data;

@Data
public class EnderecoResultDto {

    private Long id;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
}