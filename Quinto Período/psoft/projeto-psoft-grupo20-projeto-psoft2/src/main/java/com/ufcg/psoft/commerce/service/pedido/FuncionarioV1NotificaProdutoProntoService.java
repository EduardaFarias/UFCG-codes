package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.exception.EstabelecimentoNaoExisteException;
import com.ufcg.psoft.commerce.exception.PedidoNaoExisteException;
import com.ufcg.psoft.commerce.exception.PedidoStatusInvalidoException;
import com.ufcg.psoft.commerce.model.Pedido;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import com.ufcg.psoft.commerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioV1NotificaProdutoProntoService implements FuncionarioNotificaProdutoProntoService{
    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;
    @Override
    public String funcionarioNotificaPedidoPronto(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(PedidoNaoExisteException::new);
        estabelecimentoRepository.findById(pedido.getEstabelecimentoId()).orElseThrow(EstabelecimentoNaoExisteException::new);
        if (!pedido.getStatusEntrega().equals("Pedido pronto")){
            throw new PedidoStatusInvalidoException();
        }
        String notificacao =  "O pedido " + pedido.getId() + " está pronto";
        System.out.println(notificacao);
        return notificacao;
    }
}
