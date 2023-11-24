package com.ufcg.psoft.commerce.service.pedido;

@FunctionalInterface
public interface EstabelecimentoExcluirPedidoService {
    void excluirPedido(Long pedidoId, Long estabelecimentoId, String estabelecimentoCodigoAcesso);
}
