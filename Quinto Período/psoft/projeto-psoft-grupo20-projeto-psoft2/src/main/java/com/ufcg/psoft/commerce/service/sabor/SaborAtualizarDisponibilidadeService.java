package com.ufcg.psoft.commerce.service.sabor;

import com.ufcg.psoft.commerce.model.Sabor;

@FunctionalInterface
public interface SaborAtualizarDisponibilidadeService {
    Sabor atualizarDisponibilidade(Long saborId, boolean disponibilidade, Long estabelecimentoId, String estabelecimentoCodigoAcesso);
}
