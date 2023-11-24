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
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    private Long id; //unico, não pode estar vazio

    @NotBlank
    private String nomeProduto; //não pode estar vazio

    @NotBlank
    private String codigoBarras; // Nao podde ser vazio

    private double valor; // não pode ser vazio

    @NotBlank
    private String nomeFabricante; // não pode ser vazio

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto that = (Produto) o;
        return Objects.equals(id, that.id); // Comparando apenas pelo ID, você pode ajustar os campos conforme necessário
    }

}
