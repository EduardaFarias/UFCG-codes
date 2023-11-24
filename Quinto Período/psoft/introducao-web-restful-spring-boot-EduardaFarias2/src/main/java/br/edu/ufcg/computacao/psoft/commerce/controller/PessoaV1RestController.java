package br.edu.ufcg.computacao.psoft.commerce.controller;

import br.edu.ufcg.computacao.psoft.commerce.model.Endereco;
import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping (
        value= "/pessoas", produces = MediaType.APPLICATION_JSON_VALUE
)

public class PessoaV1RestController {
    private Map<Long, Pessoa> pessoaRepository;
    private Long nextId;

    public PessoaV1RestController() {
        this.pessoaRepository = new HashMap<>();
        this.nextId = 1L;
    }

    /**
     * Cria e cadastra uma pessoa
     * Task: Post
     * @param pessoa
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<?> criarPessoa(@RequestBody Pessoa pessoa) {
        if(validacaoEntradas(pessoa)){
            return  ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Por favor verifique se todos os campos estão preenchidos!");
        }
        if(verificaPessoaExiste(pessoa)){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Essa pessoa já está cadastrada!");
        }

        pessoa.setId(nextId++);
        this.pessoaRepository.put(pessoa.getId(), pessoa);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pessoa);
    }

    /**
     * Exibe uma Pessoa a partir de um ID
     * Task: get-one
     */
    @GetMapping("/{id}/get-one")
    public ResponseEntity<?> getOne(
            @PathVariable("id") Long id
    ){
        if(pessoaRepository.containsKey(id)) {
            Pessoa pessoa = pessoaRepository.get(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(pessoa);
        }else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Pessoa não encontrada");
        }
    }

    /**
     * Lista todas as pessoas cadastradas
     * Task: Get all
     * @return
     */
    @GetMapping("/get-all")
    public ResponseEntity<?> listarPessoas() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.pessoaRepository.values());
    }



    /**
     * Atualiza todos os dados de uma pessoa, exceto nome e cpf
     * Task: put
     * @param id
     * @param pessoaAtualizada
     * @return
     */
    // {id} especificar a pessoa que a gente tá atualizando
    @PutMapping("/{id}/update")
    public ResponseEntity<?> atualizarDados(
            @PathVariable("id") Long id,
            @RequestBody Pessoa pessoaAtualizada
    ) {
        if (pessoaRepository.containsKey(id)) {
            Pessoa pessoa = pessoaRepository.get(id);

            //Atualizar tudo, menos nome e cpf
            pessoa.setEmail(pessoaAtualizada.getEmail());
            pessoa.setTelefone(pessoaAtualizada.getTelefone());
            pessoa.setDataNascimento(pessoaAtualizada.getDataNascimento());
            pessoa.setProfissao(pessoaAtualizada.getProfissao());

            pessoaRepository.put(id, pessoa);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(pessoa);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Pessoa não encontrada");
        }
    }

    /**
     * Atualização simples, atualiza o email
     * Task: 1 patch
     * @param id
     * @param pessoaAtualizada
     * @return
     */
    @PatchMapping("/{id}/update-email")
    public ResponseEntity<?> atualizarEmail(
            @PathVariable("id") Long id,
            @RequestBody Pessoa pessoaAtualizada // recebe os dados fornecidos na solicitação PATCH
    ) {
        if (pessoaRepository.containsKey(id)) {
            Pessoa pessoa = pessoaRepository.get(id);
            pessoa.setEmail(pessoaAtualizada.getEmail()); //atualiza pessoa original com o que recebi em pessoa atualizada

            pessoaRepository.put(id, pessoa);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(pessoa);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Pessoa não encontrada");
        }
    }


    /**
     * Deleta um cliente a partir de ID
     * Task: Delete
     * @param id
     * @return
     */
    @DeleteMapping("/{id}/delete-one")
    public ResponseEntity<?> deleteOne(
            @PathVariable("id") Long id
    ){
        if(pessoaRepository.containsKey(id)){
            pessoaRepository.remove(id);
            return  ResponseEntity
                    .status(HttpStatus.NO_CONTENT) // 204, operação bem sucedida
                    .build();
        }else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Pessoa não encontrada");
        }
    }


    /**
     * Deleta todas as pessoas cadastradas
     * TasK: Delete
     * @return
     */
    @DeleteMapping("/delete-all")
    public ResponseEntity<?> limparBancoDados() {
        pessoaRepository.clear(); // Limpa todos os registros
        nextId = 1L; // Reinicia o próximo ID

        return ResponseEntity
                .ok("Banco de dados limpo com sucesso");
    }

    /**
     * Associa os IDs dos endereços a uma pessoa que cadastra esses endereços
     * @param pessoaId
     * @param enderecoId
     * @return
     */
    @PutMapping("/{id}/add-address/{enderecoId}")
    public ResponseEntity<?> adicionarEndereco(
            @PathVariable("id") Long pessoaId,
            @PathVariable("enderecoId") Long enderecoId
    ) {
        if (pessoaRepository.containsKey(pessoaId)) {
            Pessoa pessoa = pessoaRepository.get(pessoaId);
            if (!pessoa.getEnderecoIDs().contains(enderecoId)) {
                pessoa.getEnderecoIDs().add(enderecoId);
                pessoaRepository.put(pessoaId, pessoa);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Endereço adicionado com sucesso");
            } else {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Endereço já associado a esta pessoa");
            }
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Pessoa não encontrada");
        }
    }

    /**
     * Lista todos os IDs de endereços relacionados a uma pessoa
     * @param pessoaId
     * @return
     */
    @GetMapping("{id}/list-ids-addresses")
    public ResponseEntity<?> listarIdsEnderecos(
            @PathVariable("id") Long pessoaId
    ) {
        if (pessoaRepository.containsKey(pessoaId)) {
            Pessoa pessoa = pessoaRepository.get(pessoaId);
            List<Long> enderecoIds = pessoa.getEnderecoIDs();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(enderecoIds);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Pessoa não encontrada");
        }
    }


    /**
     * Verifica Se as entradas são válidas
     * @param pessoa
     * @return
     */
    private boolean validacaoEntradas(Pessoa pessoa) {
        return pessoa.getNome() == null
                || pessoa.getCpf() == null
                || pessoa.getEmail() == null
                || pessoa.getDataNascimento() == null;
    }

    /**
     * Verifica se a pessoa já está cadastrada
     * @param pessoa
     * @return
     */
    private boolean verificaPessoaExiste(Pessoa pessoa) {
        for (Pessoa existingPessoa : pessoaRepository.values()) {
            if (existingPessoa.getCpf().equalsIgnoreCase(pessoa.getCpf())
                    || existingPessoa.getEmail().equalsIgnoreCase(pessoa.getEmail())
                    || (existingPessoa.getNome().equalsIgnoreCase(pessoa.getNome())
                    && existingPessoa.getDataNascimento().equalsIgnoreCase(pessoa.getDataNascimento()))) {
                return true;
            }
        }
        return false;
    }
}
