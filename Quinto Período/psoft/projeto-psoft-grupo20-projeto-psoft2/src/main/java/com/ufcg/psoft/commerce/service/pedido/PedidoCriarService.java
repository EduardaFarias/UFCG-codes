package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.dto.pedido.PedidoPostPutRequestDTO;
import com.ufcg.psoft.commerce.model.Pedido;

@FunctionalInterface
public interface PedidoCriarService {
    Pedido criarPedido(PedidoPostPutRequestDTO pedidoPostPutRequestDTO, Long clienteId, String clienteCodigoAcesso, Long estabelecimentoId);
}
