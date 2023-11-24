package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.exception.ClienteNaoExisteException;
import com.ufcg.psoft.commerce.exception.CodigoInvalidoException;
import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import com.ufcg.psoft.commerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteV1ExcluirTodosPedidosService implements ClienteExcluirTodosPedidosService{
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    PedidoRepository pedidoRepository;
    @Override
    public void excluirTodosPedidos(Long clienteId, String codigoAcessoCliente) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(ClienteNaoExisteException::new);
        if(!cliente.getCodigoAcesso().equals(codigoAcessoCliente)){
            throw new CodigoInvalidoException();
        }
        pedidoRepository.deleteAllByClienteId(clienteId);
    }
}
