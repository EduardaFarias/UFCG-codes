package com.ufcg.psoft.commerce.dto.pedido;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufcg.psoft.commerce.model.Pizza;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoPostPutRequestDTO {
    @JsonProperty("enderecoEntrega")
    private String enderecoEntrega;

    @JsonProperty("pizzas")
    @ManyToOne
    @NotEmpty(message = "Pizzas obrigatorias")
    private List<Pizza> pizzas;
}
