package br.edu.ufcg.computacao.psoft.commerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Objects;

import br.edu.ufcg.computacao.psoft.commerce.model.Logradouro;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class Pessoa {
    @Id
    private Long id; // unico, NAO PODE SER VAZIO

    @NotBlank
    private String nome; // não pode ser atualizado, NAO PODE SER VAZIO

    @Column(unique = true)
    @NotBlank
    private String cpf; // unico, e não pode ser atualizado, NAO PODE SER VAZIO

    @Column(unique = true)
    @NotBlank
    private String email; // unico, NAO PODE SER VAZIO

    @NotEmpty
    private List<String> listaTelefones; // NÃO PODE SER VAZIO

    private String dataNascimento; // OPCIONAL

    @NotEmpty
    private List<Logradouro> listaEnderecos; // NÃO PODE SER VAZIO

    @NotBlank
    @Column(unique = true)
    private String profissao; // unica NÃO PODE SER VAZIO

}
