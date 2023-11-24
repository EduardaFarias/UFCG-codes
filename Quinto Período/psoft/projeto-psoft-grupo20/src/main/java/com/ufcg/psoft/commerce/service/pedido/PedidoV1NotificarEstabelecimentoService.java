package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.exception.EstabelecimentoNaoExisteException;
import com.ufcg.psoft.commerce.exception.PedidoNaoExisteException;
import com.ufcg.psoft.commerce.exception.PedidoStatusInvalidoException;
import com.ufcg.psoft.commerce.model.Estabelecimento;
import com.ufcg.psoft.commerce.model.Pedido;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import com.ufcg.psoft.commerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoV1NotificarEstabelecimentoService implements PedidoNotificarEstabelecimentoService{


    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @Override
    public String notificar(Long pedidoId){
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(PedidoNaoExisteException::new);

        if(!pedido.getStatusEntrega().equals("Pedido entregue")){
            throw new PedidoStatusInvalidoException();
        }

        Estabelecimento estabelecimento = estabelecimentoRepository
                .findById(pedido.getEstabelecimentoId()).orElseThrow(EstabelecimentoNaoExisteException::new);

        String notificacao = "Pedido entregue! : "
                + estabelecimento.getNome() + ", o pedido -"
                + pedido.getId() + "- foi marcado como entregue";

        System.out.println(notificacao);
        return notificacao;
    }
}