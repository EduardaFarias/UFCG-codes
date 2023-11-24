package br.edu.ufcg.computacao.psoft.commerce.model;


import lombok.Builder;
import lombok.Data;

import java.util.List;

//
@Data
@Builder
public class Pessoa {
    private Long id;
    private String nome;
    private String cpf; // Unico
    private String email; // unico
    private String telefone;
    private String dataNascimento;
    private String profissao;
    private List<Long> enderecoIDs;


}
