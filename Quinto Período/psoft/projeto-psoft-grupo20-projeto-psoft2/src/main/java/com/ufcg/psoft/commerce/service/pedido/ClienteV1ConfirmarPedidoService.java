package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.dto.pedido.PedidoPostPutRequestDTO;
import com.ufcg.psoft.commerce.exception.*;
import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.model.Entregador;
import com.ufcg.psoft.commerce.model.Estabelecimento;
import com.ufcg.psoft.commerce.model.Pedido;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import com.ufcg.psoft.commerce.repository.EntregadorRepository;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import com.ufcg.psoft.commerce.repository.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class ClienteV1ConfirmarPedidoService implements ClienteConfirmarPedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    EntregadorRepository entregadorRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PedidoNotificarEstabelecimentoService pedidoNotificarEstabelecimentoService;

    @Override
    public Pedido confirmar(Long pedidoId, Long ClienteId, String codigoAcessoCLiente, PedidoPostPutRequestDTO pedidoPostPutRequestDTO){

        Cliente cliente = clienteRepository.findById(ClienteId).orElseThrow(ClienteNaoExisteException::new);
        if(!cliente.getCodigoAcesso().equals(codigoAcessoCLiente)){
            throw new CodigoInvalidoException();
        }

        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(PedidoNaoExisteException::new);
        if (!pedido.getStatusEntrega().equals("Pedido em rota")){
            throw new PedidoStatusInvalidoException();
        }
        // atualizando o estabelecimento, pois quando o entregador entrega ele
        // fica disponivel para outra entrega
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(pedido.getEstabelecimentoId()).orElseThrow(EstabelecimentoNaoExisteException::new);
        Entregador entregador = entregadorRepository.findById(pedido.getEntregadorId()).orElseThrow(EntregadorNaoExisteExeption::new);
        LinkedList<Entregador> entregadoresDisponiveis = new LinkedList<>(estabelecimento.getEntregadoresDisponiveis());
        entregadoresDisponiveis.add(entregador);
        estabelecimento.setEntregadoresDisponiveis(entregadoresDisponiveis);
        modelMapper.map(estabelecimento, estabelecimentoRepository.findById(estabelecimento.getId()).get());
        estabelecimentoRepository.save(estabelecimento);

        pedido.setStatusEntrega("Pedido entregue");
        modelMapper.map(pedidoPostPutRequestDTO, pedido);
        pedidoRepository.save(pedido);
        pedidoNotificarEstabelecimentoService.notificar(pedidoId);
        return pedido;
    }
}
