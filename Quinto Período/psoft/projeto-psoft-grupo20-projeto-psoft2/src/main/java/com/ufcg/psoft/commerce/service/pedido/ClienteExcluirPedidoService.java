package com.ufcg.psoft.commerce.service.pedido;
@FunctionalInterface
public interface ClienteExcluirPedidoService {
    void excluirPedido(Long pedidoId,Long clienteId, String clienteCodigoAcesso);
}
