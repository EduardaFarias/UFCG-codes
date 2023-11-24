package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.exception.ClienteNaoExisteException;
import com.ufcg.psoft.commerce.exception.CodigoInvalidoException;
import com.ufcg.psoft.commerce.exception.EstabelecimentoNaoExisteException;
import com.ufcg.psoft.commerce.exception.PedidoNaoExisteException;
import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.model.Estabelecimento;
import com.ufcg.psoft.commerce.model.Pedido;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import com.ufcg.psoft.commerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteV1BuscarPedidoByEstabelecimentoService implements ClienteBuscarPedidoByEstabelecimentoService{
    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;
    @Override
    public List<Pedido> buscarPedidoByEstabelecimento(Long clienteId, Long estabelecimentoId, Long pedidoId, String clienteCodigoAcesso) {
        estabelecimentoRepository.findById(estabelecimentoId).orElseThrow(EstabelecimentoNaoExisteException::new);
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(PedidoNaoExisteException::new);
        Cliente clientePedido = clienteRepository.findById(pedido.getClienteId()).orElseThrow(ClienteNaoExisteException::new);
        Cliente clienteBuscador = clienteRepository.findById(clienteId).orElseThrow(ClienteNaoExisteException::new);
        if (!clientePedido.getCodigoAcesso().equals(clienteBuscador.getCodigoAcesso())){
            throw new CodigoInvalidoException();
        }
        List<Pedido> pedidosCliente = pedidoRepository.findByClienteIdAndEstabelecimentoId(clienteId, estabelecimentoId);
        return pedidosCliente;
    }
}
