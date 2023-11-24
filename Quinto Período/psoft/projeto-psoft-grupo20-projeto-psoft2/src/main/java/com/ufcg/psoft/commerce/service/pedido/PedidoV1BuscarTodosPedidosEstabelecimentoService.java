package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.exception.CodigoInvalidoException;
import com.ufcg.psoft.commerce.exception.EstabelecimentoNaoExisteException;
import com.ufcg.psoft.commerce.model.Estabelecimento;
import com.ufcg.psoft.commerce.model.Pedido;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import com.ufcg.psoft.commerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PedidoV1BuscarTodosPedidosEstabelecimentoService implements PedidoBuscarTodosPedidosEstabelecimentoService{
    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    PedidoRepository pedidoRepository;


    @Override
    public List<Pedido> buscarPedidosEstabelecimento(Long estabelecimentoId, String estabelecimentoCodigoAcesso) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(estabelecimentoId).orElseThrow(EstabelecimentoNaoExisteException::new);
        if (!estabelecimento.getCodigoAcesso().equals(estabelecimentoCodigoAcesso)){
            throw new CodigoInvalidoException();
        }
        List<Pedido> pedidosEstabelecimento = pedidoRepository.findAllByEstabelecimentoId(estabelecimentoId);
        return pedidosEstabelecimento;
    }
}
