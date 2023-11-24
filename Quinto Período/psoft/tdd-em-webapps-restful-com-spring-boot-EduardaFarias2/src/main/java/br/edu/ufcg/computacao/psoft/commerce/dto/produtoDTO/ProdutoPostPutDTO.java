package br.edu.ufcg.computacao.psoft.commerce.dto.produtoDTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class
ProdutoPostPutDTO {
    @JsonProperty("id")
    private Long id; //unico, não pode estar vazio

    @NotBlank
    @JsonProperty("nomeProduto")
    private String nomeProduto; //não pode estar vazio

    @NotBlank
    @JsonProperty("codigoBarras")
    private String codigoBarras; // Nao podde ser vazio

    @JsonProperty("valor")
    private double valor; // não pode ser vazio

    @NotBlank
    @JsonProperty("nomeFabricante")
    private String nomeFabricante; // não pode ser vazio
}
