package com.ufcg.psoft.commerce.dto.sabor;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaborPostPutRequestDTO {

    @JsonProperty("nome")
    @Column(nullable = false)
    @NotBlank(message = "Nome obrigatorio")
    private String nome;

    @JsonProperty("tipo")
    @NotBlank(message = "Tipo obrigatorio")
    @Column(nullable = false)
    @Pattern(regexp = "salgado|doce", message = "Tipo deve ser salgado ou doce")
    private String tipo;

    @JsonProperty("precoM")
    @NotNull(message = "PrecoM obrigatorio")
    @Positive(message = "PrecoM deve ser maior que zero")
    @Column(nullable = false)
    private Double precoM;

    @JsonProperty("precoG")
    @NotNull(message = "PrecoG obrigatorio")
    @Positive(message = "PrecoG deve ser maior que zero")
    @Column(nullable = false)
    private Double precoG;

    @JsonProperty("disponivel")
    @Builder.Default
    @Column(name = "disponivel")
    @NotNull(message = "Disponibilidade obrigatoria")
    private Boolean disponivel = true;
}
