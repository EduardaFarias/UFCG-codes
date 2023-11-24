package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.dto.pedido.PedidoPostPutRequestDTO;
import com.ufcg.psoft.commerce.exception.*;
import com.ufcg.psoft.commerce.model.*;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import com.ufcg.psoft.commerce.repository.PedidoRepository;
import com.ufcg.psoft.commerce.repository.SaborRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoV1CriarService implements PedidoCriarService{
    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    SaborRepository saborRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Pedido criarPedido(PedidoPostPutRequestDTO pedidoPostPutRequestDTO, Long clienteId, String clienteCodigoAcesso, Long estabelecimentoId) {
        estabelecimentoRepository.findById(estabelecimentoId).orElseThrow(EstabelecimentoNaoExisteException::new);
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(ClienteNaoExisteException::new);
        if (!cliente.getCodigoAcesso().equals(clienteCodigoAcesso)){
            throw new CodigoInvalidoException();
        }
        //endereco não informado o pedido é entregue no endereco principal
        if (pedidoPostPutRequestDTO.getEnderecoEntrega() == null
            || pedidoPostPutRequestDTO.getEnderecoEntrega().isEmpty()){
            pedidoPostPutRequestDTO.setEnderecoEntrega(cliente.getEndereco());
        }

        Pedido pedido = modelMapper.map(pedidoPostPutRequestDTO, Pedido.class);

        List<Pizza> pizzas = pedido.getPizzas();
        double valorTotal = 0;
        for (Pizza pizza : pizzas) {
            Sabor sabor1 = pizza.getSabor1();
            Sabor sabor2 = pizza.getSabor2();
            String tamanho = pizza.getTamanho();

            if (sabor1 == null) {
                throw new PizzaTamanhoInvalidoException();
            }

            if (!sabor1.isDisponivel()) {
                throw new SaborNaoDisponivelException();
            }

            if (sabor2 != null) {
                if (!sabor2.isDisponivel()) {
                    throw new SaborNaoDisponivelException();
                }
            }

            if (tamanho.equalsIgnoreCase("media")) {
                valorTotal += sabor1.getPrecoM();
            } else if (tamanho.equalsIgnoreCase("grande") && sabor2 == null) {
                valorTotal += sabor1.getPrecoG();
            } else {
                double valorSabor1 = sabor1.getPrecoG();
                double valorSabor2 = (sabor2 != null) ? sabor2.getPrecoG() : 0.0;
                valorTotal += (valorSabor1 + valorSabor2) / 2;
            }
        }


        pedido.setPreco(valorTotal);
        pedido.setPizzas(pedidoPostPutRequestDTO.getPizzas());
        pedido.setClienteId(clienteId);
        pedido.setEstabelecimentoId(estabelecimentoId);
        return pedidoRepository.save(pedido);
    }
}
