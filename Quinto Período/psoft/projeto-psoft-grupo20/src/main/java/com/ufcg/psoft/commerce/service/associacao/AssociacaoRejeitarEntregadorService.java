package com.ufcg.psoft.commerce.service.associacao;

import com.ufcg.psoft.commerce.model.Associacao;

@FunctionalInterface
public interface AssociacaoRejeitarEntregadorService {
    public Associacao rejeitarEntregador(Long entregadorId,Long estabelecimentoId, String codigoAcessoEstabelecimento);
}
