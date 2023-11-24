package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.dto.pedido.PedidoPostPutRequestDTO;
import com.ufcg.psoft.commerce.model.Pedido;

@FunctionalInterface
public interface PedidoConfirmarPagamentoService {

    Pedido confirmaPagamento(Long clienteId, String codigoAcessoCliente, Long pedidoId, String metodoPagamento, PedidoPostPutRequestDTO pedidoPostPutRequestDTO);
}
