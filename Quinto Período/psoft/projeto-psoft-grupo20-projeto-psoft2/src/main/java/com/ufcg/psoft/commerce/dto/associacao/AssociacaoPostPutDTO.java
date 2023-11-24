package com.ufcg.psoft.commerce.dto.associacao;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssociacaoPostPutDTO {
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
