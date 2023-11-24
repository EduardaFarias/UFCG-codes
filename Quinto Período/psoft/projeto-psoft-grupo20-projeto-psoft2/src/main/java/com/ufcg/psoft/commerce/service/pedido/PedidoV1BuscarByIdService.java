package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.exception.ClienteNaoExisteException;
import com.ufcg.psoft.commerce.exception.CodigoInvalidoException;
import com.ufcg.psoft.commerce.exception.PedidoNaoExisteException;
import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.model.Pedido;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import com.ufcg.psoft.commerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PedidoV1BuscarByIdService implements PedidoBuscarByIdService{
    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ClienteRepository clienteRepository;
    @Override
    public List<Pedido> buscarById(Long pedidoId, Long clienteId) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(PedidoNaoExisteException::new);
        Cliente clientedoPedido = clienteRepository.findById(pedido.getClienteId()).orElseThrow(ClienteNaoExisteException::new);
        Cliente clienteAcessando = clienteRepository.findById(clienteId).orElseThrow(ClienteNaoExisteException::new);
        if (!clientedoPedido.getCodigoAcesso().equals(clienteAcessando.getCodigoAcesso())){
            throw new CodigoInvalidoException();
        }
        List<Pedido> pedidosCliente = pedidoRepository.findByClienteIdAndId(clienteId, pedidoId);

        return pedidosCliente;
    }
}
