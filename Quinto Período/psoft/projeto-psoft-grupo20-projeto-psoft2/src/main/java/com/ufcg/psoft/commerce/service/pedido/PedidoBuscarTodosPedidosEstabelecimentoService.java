package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.model.Pedido;

import java.util.List;

@FunctionalInterface
public interface PedidoBuscarTodosPedidosEstabelecimentoService {
    List<Pedido> buscarPedidosEstabelecimento(Long estabelecimentoId, String estabelecimentoCodigoAcesso);
}
