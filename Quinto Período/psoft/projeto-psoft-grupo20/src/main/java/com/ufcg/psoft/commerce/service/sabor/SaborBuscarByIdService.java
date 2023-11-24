package com.ufcg.psoft.commerce.service.sabor;

import com.ufcg.psoft.commerce.model.Sabor;

@FunctionalInterface
public interface SaborBuscarByIdService {
    Sabor buscar(Long saborId, Long estabelecimentoId, String estabelecimentoCodigoAcesso);
}
