package com.ufcg.psoft.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "estabelecimentos")
public class Estabelecimento {

    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonProperty("codigoAcesso")
    @NotBlank(message = "Código de acesso é obrigatório")
    @Column(nullable = false)
    private String codigoAcesso;

    @NotBlank(message = "O nome do estabelecimento é obrigatório!")
    @JsonProperty("nome")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "O endereco do estabelecimento é obrigatório!")
    @JsonProperty("endereco")
    @Column(nullable = false)
    private String endereco;

    @NotBlank(message = "O telefone do estabelecimento é obrigatório!")
    @JsonProperty("telefone")
    @Column(nullable = false)
    private String telefone;

    @NotBlank(message = "O horário do estabelecimento é obrigatório")
    @JsonProperty("horario")
    @Column(nullable = false)
    private String horario;

    @JsonProperty("sabores")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Sabor> sabores;

    @JsonProperty("entregadores")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @JoinColumn(name = "entregadores")
    private List<Entregador> entregadoresDisponiveis = new LinkedList<>();
}
