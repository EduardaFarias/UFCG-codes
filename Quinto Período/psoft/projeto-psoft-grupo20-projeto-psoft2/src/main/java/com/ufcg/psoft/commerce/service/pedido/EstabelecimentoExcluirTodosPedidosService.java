package com.ufcg.psoft.commerce.service.pedido;

@FunctionalInterface
public interface EstabelecimentoExcluirTodosPedidosService {
    void excluirTodosPedido(Long estabelecimentoId, String estabelecimentoCodigoAcesso);
}
