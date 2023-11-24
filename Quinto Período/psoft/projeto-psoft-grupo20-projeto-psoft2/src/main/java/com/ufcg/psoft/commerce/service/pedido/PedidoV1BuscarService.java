package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.model.Pedido;
import com.ufcg.psoft.commerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PedidoV1BuscarService implements PedidoBuscarService{

    @Autowired
    PedidoRepository pedidoRepository;

    @Override
    public List<Pedido> buscarPedidos(){
        return pedidoRepository.findAll();
    }
}
