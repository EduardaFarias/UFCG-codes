package br.edu.ufcg.computacao.psoft.commerce.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Endereco {
    private Long id;
    private String tipoLogradouro; //rua, avenida, alameda, travessa
    private String logradouro;
    private int numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;

}
