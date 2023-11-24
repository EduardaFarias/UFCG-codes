package com.ufcg.psoft.commerce.service.sabor;

@FunctionalInterface
public interface SaborExcluirService {
    void excluirSabor(Long saborId, Long estabelecimentoId, String estabelecimentoCodigoAcesso);
}
