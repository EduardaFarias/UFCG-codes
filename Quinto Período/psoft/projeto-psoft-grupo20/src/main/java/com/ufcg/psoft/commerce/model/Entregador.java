package com.ufcg.psoft.commerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "entregadores")
public class Entregador {

    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "codigoAcesso", nullable = false)
    @NotBlank(message = "Codigo de acesso obrigatorio")
    @JsonIgnore
    private String codigoAcesso;

    @JsonProperty("nome")
    @NotBlank(message = "Nome e obrigatorio")
    @Column(name = "nomes", nullable = false)
    private String nome;

    @JsonProperty("placa do veiculo")
    @NotBlank(message = "Placa do veiculo e obrigatoria")
    @Column(name = "placas", nullable = false)
    private String placaVeiculo;

    @JsonProperty("tipo do veiculo")
    @NotBlank(message = "Tipo do veiculo e obrigatorio")
    @Pattern(regexp = "moto|carro", message = "Tipo do veiculo deve ser moto ou carro")
    @Column(name = "tipos", nullable = false)
    private String tipoVeiculo;

    @JsonProperty("cor do veiculo")
    @NotBlank(message = "Cor do veiculo e obrigatoria")
    @Column(name = "cores", nullable = false)
    private String corVeiculo;

    @JsonProperty("status")
    @Column(name = "status", nullable = false, columnDefinition = "boolean default false" )
    private boolean aprovado;

    @JsonProperty("disponibilidade")
    @Column(name = "disponibilidade", nullable = false, columnDefinition = "boolean default false")
    private boolean disponivel;
}