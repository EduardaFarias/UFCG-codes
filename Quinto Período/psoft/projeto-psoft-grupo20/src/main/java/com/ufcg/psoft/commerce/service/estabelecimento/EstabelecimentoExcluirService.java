package com.ufcg.psoft.commerce.service.estabelecimento;

@FunctionalInterface
public interface EstabelecimentoExcluirService {
    public void excluir(Long id, String codigoAcesso);
}
