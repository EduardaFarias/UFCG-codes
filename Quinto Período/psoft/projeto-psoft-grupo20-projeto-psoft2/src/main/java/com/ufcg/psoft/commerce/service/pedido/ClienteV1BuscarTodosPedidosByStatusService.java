package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.exception.ClienteNaoExisteException;
import com.ufcg.psoft.commerce.exception.CodigoInvalidoException;
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
public class ClienteV1BuscarTodosPedidosByStatusService implements ClienteBuscarTodosPedidosByStatusService{
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    PedidoRepository pedidoRepository;
    @Override
    public List<Pedido> buscarPedidosByStatus(Long clienteId, Long estabelecimentoId, String pedidoStatusEntrega, String clienteCodigoAcesso) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(ClienteNaoExisteException::new);
        estabelecimentoRepository.findById(estabelecimentoId).orElseThrow(EstabelecimentoNaoExisteException::new);

        if (!cliente.getCodigoAcesso().equals(clienteCodigoAcesso)){
            throw new CodigoInvalidoException();
        }

        List<Pedido> pedidosCliente = pedidoRepository.findAllByClienteIdAndEstabelecimentoIdAndAndStatusEntrega(clienteId, estabelecimentoId, pedidoStatusEntrega);
        return pedidosCliente;
    }
}
