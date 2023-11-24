package com.ufcg.psoft.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sabores")
public class Sabor {
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

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
    @Column(nullable = false)
    private Double precoM;

    @JsonProperty("precoG")
    @NotNull(message = "PrecoM obrigatorio")
    @Column(nullable = false)
    private Double precoG;

    @JsonProperty("disponivel")
    @Builder.Default
    @Column(nullable = false)
    @NotNull(message = "Disponibilidade obrigatoria")
    private boolean disponivel = true;

    @JsonProperty
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "clientes_interessados")
    private Set<Cliente> clientesInteressados = new HashSet<>();
}
