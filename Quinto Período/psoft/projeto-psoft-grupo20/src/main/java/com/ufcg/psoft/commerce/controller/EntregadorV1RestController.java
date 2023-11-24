package com.ufcg.psoft.commerce.controller;

import com.ufcg.psoft.commerce.dto.entregador.EntregadorPostPutRequestDTO;
import com.ufcg.psoft.commerce.model.Entregador;
import com.ufcg.psoft.commerce.service.cliente.*;
import com.ufcg.psoft.commerce.service.entregador.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/entregadores", produces = MediaType.APPLICATION_JSON_VALUE
)
public class EntregadorV1RestController {
    @Autowired
    EntregadorCriarService entregadorCriarService;

    @Autowired
    EntregadorBuscarService entregadorBuscarService;

    @Autowired
    EntregadorBuscarByIdService entregadorBuscarByIdService;

    @Autowired
    EntregadorAtualizarService entregadorAtualizarService;

    @Autowired
    EntregadorExcluirService entregadorExcluirService;

    @Autowired
    EntregadorBuscarTodosByStatusDisponibilidade entregadorBuscarTodosByStatusDisponibilidade;

    @PostMapping("/post")
    ResponseEntity<?> criarEntregador(
            @RequestBody @Valid EntregadorPostPutRequestDTO entregadorPostPutRequestDTO
    ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(entregadorCriarService.criar(entregadorPostPutRequestDTO));
    }

    @GetMapping("/get-all")
    ResponseEntity<?> buscarTodosEntregadores(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entregadorBuscarService.buscarEntregadores());
    }

    @GetMapping("/get-all/{status}/{disponibilidade}")
    ResponseEntity<?> buscarTodosEntregadoresByStatusDisponibilidade(
            @PathVariable boolean status,
            @PathVariable boolean disponibilidade
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entregadorBuscarTodosByStatusDisponibilidade.buscarByStatusDisponibilidade(status, disponibilidade));
    }
    @GetMapping("/{id}/get-one")
    ResponseEntity<?> buscarById(
            @PathVariable("id") Long id
    ){
     return ResponseEntity
             .status(HttpStatus.OK)
             .body(entregadorBuscarByIdService.buscar(id));
    }

    @PutMapping("/{id}/put")
    ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestParam String codigoAcesso,
            @RequestBody @Valid EntregadorPostPutRequestDTO entregadorPostPutRequestDTO
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entregadorAtualizarService.atualizar(id, codigoAcesso, entregadorPostPutRequestDTO));
    }

    @DeleteMapping("{id}/delete")
    ResponseEntity<?> excluir(
            @PathVariable Long id,
            @RequestParam String codigoAcesso
    ){
        entregadorExcluirService.excluir(id, codigoAcesso);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }



}
