package br.edu.ufcg.computacao.psoft.commerce.dto.pessoaDTO;

import br.edu.ufcg.computacao.psoft.commerce.model.Logradouro;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaPostPutDTO {
    @JsonProperty("id")
    private Long id;

    @NotBlank
    @JsonProperty("nome")
    private String nome;

    @Column(unique = true)
    @NotBlank
    @JsonProperty("cpf")
    private String cpf;


    @NotBlank
    @Column(unique = true)
    @JsonProperty("email")
    private String email;

    @NotEmpty
    @JsonProperty("listaTelefones")
    private List<String> listaTelefones;

    @JsonProperty("dataNascimento")
    private String dataNascimento;

    @NotEmpty
    @JsonProperty("listaEnderecos")
    private List< Logradouro> listaEnderecos;

    @Column(unique = true)
    @NotBlank
    @JsonProperty("profissao")
    private String profissao;
}
