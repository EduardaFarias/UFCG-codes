package com.ufcg.psoft.commerce.service.entregador;

import com.ufcg.psoft.commerce.dto.entregador.EntregadorPostPutRequestDTO;
import com.ufcg.psoft.commerce.model.Entregador;

@FunctionalInterface
public interface EntregadorAtualizarService {
    public Entregador atualizar(Long id, String codigoAcesso, EntregadorPostPutRequestDTO entregadorPostPutRequestDTO);
}
