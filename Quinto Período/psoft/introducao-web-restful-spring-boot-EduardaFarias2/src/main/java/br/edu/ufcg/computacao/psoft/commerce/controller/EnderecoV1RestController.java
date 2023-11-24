package br.edu.ufcg.computacao.psoft.commerce.controller;
import br.edu.ufcg.computacao.psoft.commerce.model.Endereco;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(
        value = "/enderecos", produces = MediaType.APPLICATION_JSON_VALUE
)
public class EnderecoV1RestController {
    private Map<Long, Endereco> enderecoRepository;
    private Long nextId;


    public EnderecoV1RestController(){
        this.enderecoRepository = new HashMap<>();
        this.nextId = 1L;
    }

    /**
     * Cria e cadastra um endereço
     * Task: Post
     * @param endereco
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<?> criarEndereco(
            @RequestBody Endereco endereco
    ){
        endereco.setId(nextId++);
        this.enderecoRepository.put(endereco.getId(), endereco);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(endereco);

    }

    /**
     * Exibe um endereço a partir de um ID
     * Task: get one
     * @param nextEnderecoId
     * @return
     */
    @GetMapping("/{id}/get-one")
    public ResponseEntity<?> getOne(
            @PathVariable("id") Long nextEnderecoId
    ){
        if(enderecoRepository.containsKey(nextEnderecoId)){
            Endereco endereco = enderecoRepository.get(nextEnderecoId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(endereco);
        }else{
            return  ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Endereço não encontrado");
        }
    }

    /**
     * Lista todos os endereços cadastrados
     * Task: Get all
     */
    @GetMapping("/get-all")
    public ResponseEntity<?> listarEnderecos() {
        return ResponseEntity
            .status(HttpStatus.OK)
                .body(this.enderecoRepository.values());
    }



    /**
     * Atualiza todos os dados de um endereço
     * Task: put
     * @param id
     * @param enderecoAtualizado
     * @return
     */
    @PutMapping("/{id}/update")
    public ResponseEntity<?> atualizarDados(
            @PathVariable("id") Long id,
            @RequestBody Endereco enderecoAtualizado
    ){
        if(enderecoRepository.containsKey(id)){
            Endereco endereco = enderecoRepository.get(id);

            endereco.setTipoLogradouro(enderecoAtualizado.getTipoLogradouro());
            endereco.setLogradouro(enderecoAtualizado.getLogradouro());
            endereco.setNumero(enderecoAtualizado.getNumero());
            endereco.setComplemento(enderecoAtualizado.getComplemento());
            endereco.setBairro(enderecoAtualizado.getBairro());
            endereco.setCidade(enderecoAtualizado.getCidade());
            endereco.setEstado(enderecoAtualizado.getEstado());
            endereco.setPais(enderecoAtualizado.getPais());
            endereco.setCep(enderecoAtualizado.getCep());

            enderecoRepository.put(id,endereco);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(endereco);
        }else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Endereço não encontrado");
        }
    }
    /**
     * Atualização simples, atualiza numero e complemento
     * Task: patch
     */
    @PatchMapping("{id}/simple-upddate")
    public ResponseEntity<?> simpleUpdate(
            @PathVariable("id") Long id,
            @RequestBody Endereco enderecoAtualizado
    ){
        if(enderecoRepository.containsKey(id)){
            Endereco endereco = enderecoRepository.get(id);
            endereco.setNumero(enderecoAtualizado.getNumero());
            endereco.setComplemento(enderecoAtualizado.getComplemento());

            enderecoRepository.put(id,endereco);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(endereco);
        }else{
            return  ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Endereço não encontrado");
        }
    }

    /**
     * Deleta um endereço a partir de um ID
     * task: delete
     * @param id
     * @return
     */
    @DeleteMapping("/{id}/delete-one")
    public ResponseEntity<?> deleteOne(
            @PathVariable("id") Long id
    ){
        if(enderecoRepository.containsKey(id)){
            enderecoRepository.remove(id);
            return  ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }else {
            return  ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Endereço não encontrado");
        }
    }

    /**
     * Deleta todos os endereços cadastrados no sistema
     * Task: delete
     * @return
     */
    @DeleteMapping("/{id}/delete-all")
    public ResponseEntity<?> deleteAll(){
        enderecoRepository.clear(); // limpa todos os registros
        nextId = 1L; // REINICIA O PRÓXIMO ID
        return  ResponseEntity
                .ok("Banco de dados limpo com sucesso!");
    }

    /**
     * Verifica se as entradas são válidas
     * @param endereco
     * @return
     */
    private boolean validacaoEntradas(Endereco endereco){
        return endereco.getTipoLogradouro() == null
                || endereco.getLogradouro() == null
                || endereco.getNumero() < 0
                || endereco.getComplemento() == null
                || endereco.getBairro() == null
                || endereco.getCidade() == null
                || endereco.getEstado() == null
                || endereco.getPais() == null
                || endereco.getCep() == null;
    }


}
