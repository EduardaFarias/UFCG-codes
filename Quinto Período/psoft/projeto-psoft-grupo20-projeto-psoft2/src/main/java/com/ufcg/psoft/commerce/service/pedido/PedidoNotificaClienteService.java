package com.ufcg.psoft.commerce.service.pedido;

@FunctionalInterface
public interface PedidoNotificaClienteService {
    String notificar(Long pedidoId);
}