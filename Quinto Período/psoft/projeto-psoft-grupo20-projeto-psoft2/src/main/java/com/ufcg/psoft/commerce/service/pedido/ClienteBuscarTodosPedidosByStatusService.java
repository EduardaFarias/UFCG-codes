package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.model.Pedido;
import org.springframework.stereotype.Service;

import java.util.List;

@FunctionalInterface
public interface ClienteBuscarTodosPedidosByStatusService {
    List<Pedido> buscarPedidosByStatus(Long clienteId, Long estabelecimentoId, String pedidoStatusEntrega, String clienteCodigoAcesso);
}
