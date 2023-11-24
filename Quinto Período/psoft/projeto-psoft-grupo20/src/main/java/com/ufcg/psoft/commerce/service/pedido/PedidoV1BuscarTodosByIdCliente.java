package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.exception.ClienteNaoExisteException;
import com.ufcg.psoft.commerce.exception.CodigoInvalidoException;
import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.model.Pedido;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import com.ufcg.psoft.commerce.repository.PedidoRepository;
import com.ufcg.psoft.commerce.service.cliente.ClienteBuscarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoV1BuscarTodosByIdCliente implements PedidoBuscarTodosByIdCliente{
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Override
    public List<Pedido> buscar(Long clienteId, String codigoAcessoCliente) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(ClienteNaoExisteException::new);
        if (!cliente.getCodigoAcesso().equals(codigoAcessoCliente)){
            throw new CodigoInvalidoException();
        }
        List<Pedido> pedidosCliente = pedidoRepository.findAllByClienteId(clienteId);
        return pedidosCliente;
    }
}
