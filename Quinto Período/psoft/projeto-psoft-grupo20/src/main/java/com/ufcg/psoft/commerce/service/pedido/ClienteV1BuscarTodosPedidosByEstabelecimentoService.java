package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.exception.ClienteNaoExisteException;
import com.ufcg.psoft.commerce.exception.EstabelecimentoNaoExisteException;
import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.model.Pedido;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import com.ufcg.psoft.commerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteV1BuscarTodosPedidosByEstabelecimentoService implements  ClienteBuscarTodosPedidosByEstabelecimentoService{
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Override
    public List<Pedido> clienteBuscarTodosPedidos(Long clienteId, Long estabelecimentoId, String clienteCodigoAcesso) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(EstabelecimentoNaoExisteException::new);
        estabelecimentoRepository.findById(estabelecimentoId).orElseThrow(EstabelecimentoNaoExisteException::new);
        if(!cliente.getCodigoAcesso().equals(clienteCodigoAcesso)){
            throw new ClienteNaoExisteException();
        }
        List<Pedido> pedidosCliente = pedidoRepository.findAllByClienteIdAndEstabelecimentoId(clienteId, estabelecimentoId);
        return pedidosCliente;
    }
}
