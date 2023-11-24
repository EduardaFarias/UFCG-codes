package br.edu.ufcg.computacao.psoft.commerce.model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class Logradouro {
    @Id
    private Long id;

    @NotBlank
    private String tipoLogradouro;

    @NotBlank
    private String nomeLogradouro;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    @NotBlank
    private String estado;

    @NotBlank
    private String pais;

    @NotBlank
    private String cep;

    @NotBlank
    private String complemento;

    // Na classe Logradouro
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Logradouro that = (Logradouro) o;
        return Objects.equals(id, that.id);

    }
}
