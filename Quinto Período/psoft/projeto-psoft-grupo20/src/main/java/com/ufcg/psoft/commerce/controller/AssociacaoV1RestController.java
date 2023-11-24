package com.ufcg.psoft.commerce.controller;

import com.ufcg.psoft.commerce.dto.associacao.AssociacaoPostPutDTO;
import com.ufcg.psoft.commerce.service.associacao.AssociacaoAprovarEntregadorService;
import com.ufcg.psoft.commerce.service.associacao.AssociacaoCriarService;
import com.ufcg.psoft.commerce.service.associacao.AssociacaoExcluirService;
import com.ufcg.psoft.commerce.service.associacao.AssociacaoRejeitarEntregadorService;
import com.ufcg.psoft.commerce.service.entregador.EntregadorCriarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/associacoes", produces = MediaType.APPLICATION_JSON_VALUE
)
public class AssociacaoV1RestController {
    @Autowired
    AssociacaoCriarService associacaoCriarService;

    @Autowired
    AssociacaoAprovarEntregadorService associacaoAprovarEntregadorService;

    @Autowired
    AssociacaoRejeitarEntregadorService associacaoRejeitarEntregadorService;

    @Autowired
    AssociacaoExcluirService associacaoExcluirService;


    @PostMapping("/post")
    ResponseEntity<?> associarEntregadorEstabelecimento(
            @RequestParam Long entregadorId,
            @RequestParam Long estabelecimentoId,
            @RequestParam String codigoAcesso
    ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(associacaoCriarService.
                        associarEntregadorEstabelecimento(entregadorId, codigoAcesso, estabelecimentoId));

    }

    @PatchMapping ("{entregadorId}/patch/status/aprovar")
    public ResponseEntity<?> aprovarAssociacao(
            @PathVariable Long entregadorId,
            @RequestParam Long estabelecimentoId,
            @RequestParam String codigoAcessoEstabelecimento
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(associacaoAprovarEntregadorService.aprovarEntregador(entregadorId, estabelecimentoId, codigoAcessoEstabelecimento));
    }

    @PatchMapping("{entregadorId}/patch/status/rejeitar")
    public ResponseEntity<?> rejeitarAssociacao(
            @PathVariable Long entregadorId,
            @RequestParam Long estabelecimentoId,
            @RequestParam String codigoAcessoEstabelecimento
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(associacaoRejeitarEntregadorService.rejeitarEntregador(entregadorId, estabelecimentoId, codigoAcessoEstabelecimento));
    }

    @DeleteMapping("/{entregadorId}/delete")
    ResponseEntity<?> removerEntregadorEstabelecimento(
            @PathVariable Long entregadorId,
            @RequestParam Long estabelecimentoId,
            @RequestParam String codigoAcessoEstabelecimento
    ){
        associacaoExcluirService.removerEntregadorEstabelecimento(entregadorId, codigoAcessoEstabelecimento, estabelecimentoId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();

    }

}
