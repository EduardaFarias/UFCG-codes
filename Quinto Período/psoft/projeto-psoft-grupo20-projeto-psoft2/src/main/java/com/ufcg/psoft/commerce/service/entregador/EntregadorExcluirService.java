package com.ufcg.psoft.commerce.service.entregador;

@FunctionalInterface
public interface EntregadorExcluirService {
    public void excluir(Long id, String codigoAcesso);
}
