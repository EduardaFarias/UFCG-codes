package com.ufcg.psoft.commerce.dto.entregador;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntregadorPostPutRequestDTO {

    @JsonProperty("codigoAcesso")
    @Column(name = "codigoAcesso", nullable = false)
    @NotBlank(message = "Codigo de acesso obrigatorio")
    @Pattern(regexp = "^\\d{6}$", message = "Codigo de acesso deve ter exatamente 6 digitos numericos")
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
