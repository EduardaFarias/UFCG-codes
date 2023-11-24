package com.ufcg.psoft.commerce.controller;

import com.ufcg.psoft.commerce.dto.estabelecimento.EstabelecimentoPostPutRequestDTO;
import com.ufcg.psoft.commerce.service.associacao.AssociacaoAprovarEntregadorService;
import com.ufcg.psoft.commerce.service.associacao.AssociacaoCriarService;
import com.ufcg.psoft.commerce.service.associacao.AssociacaoExcluirService;
import com.ufcg.psoft.commerce.service.associacao.AssociacaoRejeitarEntregadorService;
import com.ufcg.psoft.commerce.service.estabelecimento.EstabelecimentoAtualizarService;
import com.ufcg.psoft.commerce.service.estabelecimento.EstabelecimentoCriarService;
import com.ufcg.psoft.commerce.service.estabelecimento.EstabelecimentoExcluirService;
import com.ufcg.psoft.commerce.service.sabor.SaborBuscarService;
import com.ufcg.psoft.commerce.service.sabor.SaborBuscarServiceByTipoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/estabelecimentos", produces = MediaType.APPLICATION_JSON_VALUE
)
public class
EstabelecimentoV1RestController {
    @Autowired
    EstabelecimentoCriarService estabelecimentoCriarService;

    @Autowired
    EstabelecimentoExcluirService estabelecimentoExcluirService;

    @Autowired
    EstabelecimentoAtualizarService estabelecimentoAtualizarService;

    @Autowired
    AssociacaoCriarService associacaoCriarService;

    @Autowired
    AssociacaoAprovarEntregadorService associacaoAprovarEntregadorService;

    @Autowired
    AssociacaoRejeitarEntregadorService associacaoRejeitarEntregadorService;

    @Autowired
    AssociacaoExcluirService associacaoExcluirService;

    @Autowired
    SaborBuscarService saborBuscarService;

    @Autowired
    SaborBuscarServiceByTipoService saborBuscarServiceByTipoService;


    @PostMapping("/post")
    ResponseEntity<?> criarEstabelecimento(
            @RequestBody @Valid EstabelecimentoPostPutRequestDTO estabelecimentoPostPutRequestDTO
    ){
        return  ResponseEntity
              .status(HttpStatus.CREATED)
              .body(estabelecimentoCriarService.criar(estabelecimentoPostPutRequestDTO));
    }

    @PutMapping("/{id}/put")
    ResponseEntity<?> atualizarEstabelecimento(
            @PathVariable Long id,
            @RequestParam String codigoAcesso,
            @RequestBody @Valid EstabelecimentoPostPutRequestDTO estabelecimentoPostPutRequestDTO
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(estabelecimentoAtualizarService.atualizar(id, codigoAcesso, estabelecimentoPostPutRequestDTO));
    }

    @DeleteMapping("{id}/delete")
    ResponseEntity<?> excluirEstabelecimento(
            @PathVariable Long id,
            @RequestParam String codigoAcesso
    ){
        estabelecimentoExcluirService.excluir(id, codigoAcesso);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PostMapping("{entregadorId}/{estabelecimentoId}/post")
    ResponseEntity<?> associarEntregadorEstabelecimento(
            @PathVariable Long entregadorId,
            @PathVariable Long estabelecimentoId,
            @RequestParam String codigoAcessoEntregador
    ){
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(associacaoCriarService.associarEntregadorEstabelecimento(entregadorId,codigoAcessoEntregador,estabelecimentoId));
    }
    @PatchMapping ("{entregadorId}/{estabelecimentoId}/patch/status/aprovar")
    public ResponseEntity<?> aprovarAssociacao(
            @PathVariable Long entregadorId,
            @PathVariable Long estabelecimentoId,
            @RequestParam String codigoAcessoEstabelecimento
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(associacaoAprovarEntregadorService.aprovarEntregador(entregadorId, estabelecimentoId, codigoAcessoEstabelecimento));
    }

    @PatchMapping("{entregadorId}/{estabelecimentoId}/patch/status/rejeitar")
    public ResponseEntity<?> rejeitarAssociacao(
            @PathVariable Long entregadorId,
            @PathVariable Long estabelecimentoId,
            @RequestParam String codigoAcessoEstabelecimento
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(associacaoRejeitarEntregadorService.rejeitarEntregador(entregadorId, estabelecimentoId, codigoAcessoEstabelecimento));
    }
    @DeleteMapping("/{entregadorId}/{estabelecimentoId}/delete")
    ResponseEntity<?> removerEntregadorEstabelecimento(
            @PathVariable Long entregadorId,
            @PathVariable Long estabelecimentoId,
            @RequestParam String codigoAcessoEstabelecimento
    ){
        associacaoExcluirService.removerEntregadorEstabelecimento(entregadorId, codigoAcessoEstabelecimento, estabelecimentoId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();

    }

    @GetMapping("/{estabelecimentoId}/sabores")
    ResponseEntity<?> buscarCardapioEstabelecimento(
            @PathVariable Long estabelecimentoId,
            @RequestParam String estabelecimentoCodigoAcesso
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saborBuscarService.buscar(estabelecimentoId, estabelecimentoCodigoAcesso));
    }

    @GetMapping("/{estabelecimentoId}/sabores/tipo")
    ResponseEntity<?> buscarCardapioByTipo(
            @PathVariable Long estabelecimentoId,
            @RequestParam String estabelecimentoCodigoAcesso,
            @RequestParam String tipo
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saborBuscarServiceByTipoService.buscar(estabelecimentoId, estabelecimentoCodigoAcesso, tipo));
    }

}
