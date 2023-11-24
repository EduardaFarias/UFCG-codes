package com.ufcg.psoft.commerce.service.associacao;

import com.ufcg.psoft.commerce.dto.associacao.AssociacaoPostPutDTO;
import com.ufcg.psoft.commerce.model.Associacao;

@FunctionalInterface
public interface AssociacaoCriarService {
    public Associacao associarEntregadorEstabelecimento(Long entregadorId, String codigoAcesso, Long estabelecimentoId);
}
