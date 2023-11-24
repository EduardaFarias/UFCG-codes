package com.ufcg.psoft.commerce.controller;

import com.ufcg.psoft.commerce.dto.cliente.ClientePostPutRequestDTO;
import com.ufcg.psoft.commerce.dto.pedido.PedidoPostPutRequestDTO;
import com.ufcg.psoft.commerce.service.cliente.*;
import com.ufcg.psoft.commerce.service.pedido.PedidoCriarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE
)
public class ClienteV1RestController {
    @Autowired
    ClienteCriarService clienteCriarService;

    @Autowired
    ClienteBuscarService clienteBuscarService;

    @Autowired
    ClienteBuscarByIdService clienteBuscarByIdService;

    @Autowired
    ClienteAtualizarService clienteAtualizarService;

    @Autowired
    ClienteExcluirService clienteExcluirService;

    @Autowired
    ClienteDemonstraInteressesSaborService clienteDemonstraInteressesSaborService;

    @Autowired
    PedidoCriarService pedidoCriarService;

    @PostMapping("/post")
    ResponseEntity<?> criarCliente(
            @RequestBody @Valid ClientePostPutRequestDTO clientePostPutRequestDTO
    ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteCriarService.criar(clientePostPutRequestDTO));
    }

    @PostMapping("/post/pedido")
    public ResponseEntity<?> criarPedido(
            @RequestBody @Valid PedidoPostPutRequestDTO pedidoPostPutRequestDTO,
            @RequestParam Long clienteId,
            @RequestParam String clienteCodigoAcesso,
            @RequestParam Long estabelecimentoId
    ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedidoCriarService.criarPedido(pedidoPostPutRequestDTO, clienteId, clienteCodigoAcesso, estabelecimentoId));
    }

    @GetMapping("/get-all")
    ResponseEntity<?> buscarTodosClientes(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteBuscarService.buscarClientes());
    }

    @GetMapping("/{id}/get-one")
    ResponseEntity<?> buscarById(
            @PathVariable("id") Long id
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteBuscarByIdService.buscar(id));

    }

    @PutMapping("/{id}/put")
    ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestParam String codigoAcesso,
            @RequestBody @Valid ClientePostPutRequestDTO clientePostPutRequestDTO
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteAtualizarService.atualizar(id, codigoAcesso, clientePostPutRequestDTO));
    }

    @PutMapping("/put/{clienteId}/demonstrarInteresse")
    ResponseEntity<?> demonstrarInteresse(
            @PathVariable Long clienteId,
            @RequestParam String clienteCodigoAcesso,
            @RequestParam Long saborId
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteDemonstraInteressesSaborService.demonstrarInteresse(clienteId, clienteCodigoAcesso, saborId ));
    }

    @DeleteMapping("{id}/delete")
    ResponseEntity<?> excluir(
            @PathVariable Long id,
            @RequestParam String codigoAcesso
    ){
        clienteExcluirService.excluir(id, codigoAcesso);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
