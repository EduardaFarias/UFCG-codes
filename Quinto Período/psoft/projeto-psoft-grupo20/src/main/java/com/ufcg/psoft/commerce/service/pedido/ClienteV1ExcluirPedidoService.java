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

@Service
public class ClienteV1ExcluirPedidoService implements ClienteExcluirPedidoService {
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    PedidoRepository pedidoRepository;
    @Override
    public void excluirPedido(Long pedidoId, Long clienteId, String clienteCodigoAcesso) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(PedidoNaoExisteException::new);
        Cliente clientePedido = clienteRepository.findById(pedido.getClienteId()).orElseThrow(ClienteNaoExisteException::new);
        Cliente clienteExcluindo = clienteRepository.findById(clienteId).orElseThrow(ClienteNaoExisteException::new);
        if (!clientePedido.getCodigoAcesso().equals(clienteExcluindo.getCodigoAcesso())){
            throw new CodigoInvalidoException();
        }
        pedidoRepository.delete(pedido);

    }
}
