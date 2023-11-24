package com.ufcg.psoft.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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
@Entity
@Table(name = "pizzas")
public class Pizza {
    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonProperty("tamanho")
    @Column(nullable = false)
    @NotBlank(message = "Tamanho obrigatorio")
    @Pattern(regexp = "media|grande", message = "Tamanho deve ser media ou grande")
    private String tamanho;

    @JsonProperty("sabor1")
    @OneToOne
    @JoinColumn(nullable = false)
    private Sabor sabor1;

    @JsonProperty("sabor2")
    @OneToOne
    private Sabor sabor2;
}
