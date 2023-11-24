package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.model.Pedido;

import java.util.List;

@FunctionalInterface
public interface ClienteBuscarPedidoByEstabelecimentoService {
    List<Pedido> buscarPedidoByEstabelecimento(Long clienteId, Long estabelecimentoId, Long pedidoId, String clienteCodigoAcesso);
}
