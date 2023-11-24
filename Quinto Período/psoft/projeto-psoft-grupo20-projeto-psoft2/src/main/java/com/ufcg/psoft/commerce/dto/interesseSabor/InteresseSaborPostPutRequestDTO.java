package com.ufcg.psoft.commerce.dto.interesseSabor;

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
public class InteresseSaborPostPutRequestDTO {
    @JsonProperty("cliente")
    @Column(nullable = false)
    private long clienteId;

    @JsonProperty("sabor")
    @Column(nullable = false)
    private long saborId;
}