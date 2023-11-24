package com.ufcg.psoft.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "associacoes")
public class Associacao {
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonProperty("entregador")
    @Column(nullable = false)
    private Long entregadorId;

    @JsonProperty("estabelecimento")
    @Column(nullable = false)
    private Long estabelecimentoId;

    @JsonProperty("status")
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean status;
}
