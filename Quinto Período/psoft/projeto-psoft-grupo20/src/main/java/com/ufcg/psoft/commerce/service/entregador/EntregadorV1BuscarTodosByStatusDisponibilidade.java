package com.ufcg.psoft.commerce.service.entregador;

import com.ufcg.psoft.commerce.model.Entregador;
import com.ufcg.psoft.commerce.repository.EntregadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregadorV1BuscarTodosByStatusDisponibilidade implements EntregadorBuscarTodosByStatusDisponibilidade{
    @Autowired
    EntregadorRepository entregadorRepository;
    @Override
    public List<Entregador> buscarByStatusDisponibilidade(boolean status, boolean disponibilidade) {
        List<Entregador> entregadores = entregadorRepository.findAllByAprovadoAndDisponivel(true, true);
        return entregadores;
    }
}
