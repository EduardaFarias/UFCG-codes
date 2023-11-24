package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.model.Pedido;

import java.util.List;

@FunctionalInterface
public interface ClienteBuscarTodosPedidosByEstabelecimentoService {
    List<Pedido> clienteBuscarTodosPedidos(Long clienteId, Long estabelecimentoId, String clienteCodigoAcesso);
}
