package com.ufcg.psoft.commerce.dto.estabelecimento;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufcg.psoft.commerce.model.Estabelecimento;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstabelecimentoPostPutRequestDTO {

    @NotBlank(message = "Código de acesso é obrigatório")
    @JsonProperty("codigoAcesso")
    @Pattern(regexp = "^\\d{6}$", message = "Codigo de acesso deve ter exatamente 6 digitos numericos")
    private String codigoAcesso;

    @NotBlank(message = "O nome do estabelecimento é obrigatório!")
    @JsonProperty("nome")
    private String nome;

    @NotBlank(message = "O endereco do estabelecimento é obrigatório!")
    @JsonProperty("endereco")
    private String endereco;

    @NotBlank(message = "O telefone do estabelecimento é obrigatório!")
    @JsonProperty("telefone")
    private String telefone;

    @NotBlank(message = "O horário do estabelecimento é obrigatório")
    @JsonProperty("horario")
    private String horario;

    public EstabelecimentoPostPutRequestDTO(Estabelecimento estabelecimento) {
        this.codigoAcesso = estabelecimento.getCodigoAcesso();
    }
}
