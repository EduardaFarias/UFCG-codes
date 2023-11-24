package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.dto.pedido.PedidoPostPutRequestDTO;
import com.ufcg.psoft.commerce.model.Pedido;

@FunctionalInterface
public interface PedidoAtualizarService {
    Pedido atualizarPedido(Long pedidoId, String codigoAcessoCliente, PedidoPostPutRequestDTO pedidoPostPutRequestDTO);

}
