package br.edu.ufcg.computacao.psoft.commerce.dto.logradouroDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogradouroPostPutDTO {
    @JsonProperty("id")
    private Long id;

    @NotBlank
    @JsonProperty("tipoLogradouro")
    private String tipoLogradouro;

    @NotBlank
    @JsonProperty("nomeLogradouro")
    private String nomeLogradouro;

    @NotBlank
    @JsonProperty("bairro")
    private String bairro;

    @NotBlank
    @JsonProperty("cidade")
    private String cidade;

    @NotBlank
    @JsonProperty("estado")
    private String estado;

    @NotBlank
    @JsonProperty("pais")
    private String pais;

    @NotBlank
    @JsonProperty("cep")
    private String cep;

    @NotBlank
    @JsonProperty("complemento")
    private String complemento;
}
