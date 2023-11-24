package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.dto.pedido.PedidoPostPutRequestDTO;
import com.ufcg.psoft.commerce.model.Pedido;

import java.util.List;

@FunctionalInterface
public interface EstabelecimentoBuscarPedidoByService {
    List<Pedido> buscarPedidoById(Long pedidoId, Long estabelecimentoId, String estabelecimentoCodigoAcesso,
                                  PedidoPostPutRequestDTO pedidoPostPutRequestDTO);
}
