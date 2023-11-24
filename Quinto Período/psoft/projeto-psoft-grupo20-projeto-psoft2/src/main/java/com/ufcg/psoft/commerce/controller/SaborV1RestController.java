package com.ufcg.psoft.commerce.controller;

import com.ufcg.psoft.commerce.dto.sabor.SaborPostPutRequestDTO;
import com.ufcg.psoft.commerce.service.sabor.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/sabores", produces = MediaType.APPLICATION_JSON_VALUE
)
public class SaborV1RestController {
    @Autowired
    SaborCriarService saborCriarService;

    @Autowired
    SaborBuscarByIdService saborBuscarByIdService;

    @Autowired
    SaborBuscarService saborBuscarService;
    @Autowired
    SaborAtualizarService saborAtualizarService;

    @Autowired
    SaborExcluirService saborExcluirService;

    @Autowired
    SaborAtualizarDisponibilidadeService saborAtualizarDisponibilidadeService;

    @Autowired
    SaborBuscarServiceByTipoService saborBuscarServiceByTipoService;

    @PostMapping("/post")
    public ResponseEntity<?> criarSabor(
            @RequestBody @Valid SaborPostPutRequestDTO saborPostPutRequestDTO,
            @RequestParam Long estabelecimentoId,
            @RequestParam String estabelecimentoCodigoAcesso){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saborCriarService.criarSabor(saborPostPutRequestDTO, estabelecimentoId, estabelecimentoCodigoAcesso));
    }

    @GetMapping("/get-one/{saborId}")
    public ResponseEntity<?> buscarById(
            @PathVariable Long saborId,
            @RequestParam Long estabelecimentoId,
            @RequestParam String estabelecimentoCodigoAcesso
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saborBuscarByIdService.buscar(saborId, estabelecimentoId, estabelecimentoCodigoAcesso));
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> buscar(
            @RequestParam Long estabelecimentoId,
            @RequestParam String estabelecimentoCodigoAcesso
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saborBuscarService.buscar(estabelecimentoId, estabelecimentoCodigoAcesso));
    }

    @GetMapping("/get/{sabor}")
    public ResponseEntity<?> buscarPorSabor(
            @RequestParam Long estabelecimentoId,
            @RequestParam String estabelecimentoCodigoAcesso,
            @RequestParam String tipo
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saborBuscarServiceByTipoService.buscar(estabelecimentoId,estabelecimentoCodigoAcesso, tipo));
    }


    @PutMapping("/put")
    public ResponseEntity<?> atualizarSabor(
            @RequestParam Long saborId,
            @RequestParam Long estabelecimentoId,
            @RequestParam String estabelecimentoCodigoAcesso,
            @RequestBody @Valid SaborPostPutRequestDTO saborPostPutRequestDTO
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saborAtualizarService.atualizar(saborId, estabelecimentoId, estabelecimentoCodigoAcesso, saborPostPutRequestDTO));
    }

    @PatchMapping("/patch/{saborId}/{disponibilidade}")
    public ResponseEntity<?> atualizarDisponibilidadeSabor(
          @PathVariable Long saborId,
          @PathVariable boolean disponibilidade,
          @RequestParam Long estabelecimentoId,
          @RequestParam String estabelecimentoCodigoAcesso
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saborAtualizarDisponibilidadeService
                        .atualizarDisponibilidade(saborId, disponibilidade, estabelecimentoId, estabelecimentoCodigoAcesso));
    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> excluirSabor(
            @RequestParam Long saborId,
            @RequestParam Long estabelecimentoId,
            @RequestParam String estabelecimentoCodigoAcesso
    ){
        saborExcluirService.excluirSabor(saborId, estabelecimentoId, estabelecimentoCodigoAcesso);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }


}
