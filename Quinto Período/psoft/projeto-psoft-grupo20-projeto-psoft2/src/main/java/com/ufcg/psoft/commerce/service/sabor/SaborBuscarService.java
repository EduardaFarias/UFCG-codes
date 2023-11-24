package com.ufcg.psoft.commerce.service.sabor;

import com.ufcg.psoft.commerce.model.Sabor;

import java.util.List;

@FunctionalInterface
public interface SaborBuscarService {
    List<Sabor> buscar(Long estabelecimentoId, String estabelecimentoCodigoAcesso);
}
