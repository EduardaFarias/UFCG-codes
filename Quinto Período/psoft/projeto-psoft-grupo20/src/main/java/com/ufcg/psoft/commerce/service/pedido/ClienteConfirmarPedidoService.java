package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.dto.pedido.PedidoPostPutRequestDTO;
import com.ufcg.psoft.commerce.model.Pedido;

@FunctionalInterface
public interface ClienteConfirmarPedidoService {
    Pedido confirmar(Long pedidoId, Long clienteId, String codigoAcessoCliente, PedidoPostPutRequestDTO pedidoPostPutRequestDTO);
}
