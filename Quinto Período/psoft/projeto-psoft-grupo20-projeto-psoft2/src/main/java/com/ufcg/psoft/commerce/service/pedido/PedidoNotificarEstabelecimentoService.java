package com.ufcg.psoft.commerce.service.pedido;

@FunctionalInterface
public interface PedidoNotificarEstabelecimentoService {
    String notificar(Long pedidoId);
}