package com.ufcg.psoft.commerce.service.pedido;

@FunctionalInterface
public interface ClienteCancelarPedidoService {
    void cancelarPedido(Long pedidoId, String clienteCodigoAcesso);
}
