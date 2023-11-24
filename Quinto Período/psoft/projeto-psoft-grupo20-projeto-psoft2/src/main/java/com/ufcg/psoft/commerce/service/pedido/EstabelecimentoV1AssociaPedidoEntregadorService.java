package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.dto.pedido.PedidoPostPutRequestDTO;
import com.ufcg.psoft.commerce.exception.*;
import com.ufcg.psoft.commerce.model.Entregador;
import com.ufcg.psoft.commerce.model.Estabelecimento;
import com.ufcg.psoft.commerce.model.Pedido;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import com.ufcg.psoft.commerce.repository.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class EstabelecimentoV1AssociaPedidoEntregadorService implements  EstabelecimentoAssociaPedidoEntregadorService{
    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    PedidoNotificaClienteService pedidoNotificaCliente;
    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public Pedido associarPedidoEntregador(Long pedidoId, Long estabelecimentoId, String estabelecimentoCodigoAcesso, PedidoPostPutRequestDTO pedidoPostPutRequestDTO) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(PedidoNaoExisteException::new);
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(estabelecimentoId).orElseThrow(EstabelecimentoNaoExisteException::new);

        if (!estabelecimento.getCodigoAcesso().equals(estabelecimentoCodigoAcesso)){
            throw new CodigoInvalidoException();
        }

        if (!pedido.getStatusEntrega().equals("Pedido pronto")){
            throw new PedidoStatusInvalidoException();
        }
        LinkedList<Entregador> entregadoresDisponiveis = new LinkedList<>(estabelecimento.getEntregadoresDisponiveis());
        if (entregadoresDisponiveis.isEmpty()){
            throw new EntregadorNaoDisponivelException();
        }

        Entregador entregadorAssociado = entregadoresDisponiveis.getFirst();

        pedido.setEntregadorId(entregadorAssociado.getId());
        entregadoresDisponiveis.remove(entregadorAssociado);
        estabelecimento.setEntregadoresDisponiveis(entregadoresDisponiveis);

        pedido.setStatusEntrega("Pedido em rota");
        modelMapper.map(estabelecimento, estabelecimentoRepository.findById(estabelecimento.getId()).get());
        estabelecimentoRepository.save(estabelecimento);
        modelMapper.map(pedidoPostPutRequestDTO, pedido);
        pedidoRepository.save(pedido);
        pedidoNotificaCliente.notificar(pedidoId);

        return pedido;
    }
}
