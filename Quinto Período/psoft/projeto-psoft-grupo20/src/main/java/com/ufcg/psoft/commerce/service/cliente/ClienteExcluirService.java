package com.ufcg.psoft.commerce.service.cliente;

@FunctionalInterface
public interface ClienteExcluirService {
    public void excluir(Long id, String codigoAcesso);
}
