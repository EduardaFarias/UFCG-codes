package com.ufcg.psoft.commerce.service.associacao;

@FunctionalInterface
public interface AssociacaoExcluirService {
    public void removerEntregadorEstabelecimento(Long entregadorId, String codigoAcesso, Long estabelecimentoId);
}
