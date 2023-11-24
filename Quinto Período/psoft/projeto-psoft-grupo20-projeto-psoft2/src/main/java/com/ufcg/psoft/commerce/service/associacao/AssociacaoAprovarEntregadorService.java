package com.ufcg.psoft.commerce.service.associacao;

import com.ufcg.psoft.commerce.model.Associacao;

@FunctionalInterface
public interface AssociacaoAprovarEntregadorService {
    public Associacao aprovarEntregador(Long entregadorId,Long estabelecimentoId, String codigoAcessoEstabelecimento);
}
