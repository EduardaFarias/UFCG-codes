package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.dto.pedido.PedidoPostPutRequestDTO;
import com.ufcg.psoft.commerce.exception.ClienteNaoExisteException;
import com.ufcg.psoft.commerce.exception.CodigoInvalidoException;
import com.ufcg.psoft.commerce.exception.PedidoNaoExisteException;
import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.model.Pedido;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import com.ufcg.psoft.commerce.repository.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoV1AtualizarService implements PedidoAtualizarService{
    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public Pedido atualizarPedido(Long pedidoId, String codigoAcessoCliente, PedidoPostPutRequestDTO pedidoPostPutRequestDTO) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(PedidoNaoExisteException::new);
        Cliente cliente = clienteRepository.findById(pedido.getClienteId()).orElseThrow(ClienteNaoExisteException::new);
        if (!cliente.getCodigoAcesso().equals(codigoAcessoCliente)){
            throw new CodigoInvalidoException();
        }
        modelMapper.map(pedidoPostPutRequestDTO, pedido);
        return pedidoRepository.save(pedido);
    }
}
