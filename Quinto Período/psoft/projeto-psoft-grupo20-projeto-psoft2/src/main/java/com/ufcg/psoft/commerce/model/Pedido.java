package com.ufcg.psoft.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonProperty("preco")
    @Column(nullable = false)
    @Positive(message = "O preco deve ser maior que 0")
    private Double preco;

    @JsonProperty("enderecoEntrega")
    @Column
    private String enderecoEntrega;

    @JsonProperty("clienteId")
    @Column(nullable = false)
    private Long clienteId;

    @JsonProperty("estabelecimentoId")
    @Column(nullable = false)
    private Long estabelecimentoId;

    @JsonProperty("entregadorId")
    @Column
    private Long entregadorId;

    @JsonProperty("statusPagamento")
    @Column(nullable = false)
    @Builder.Default
    private Boolean statusPagamento = false;

    @JsonProperty("statusEntrega")
    @Column(nullable = false)
    @Builder.Default
    private String statusEntrega = "Pedido Recebido";

    @JsonProperty
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "pizzas")
    private List<Pizza> pizzas = new ArrayList<>();
}
