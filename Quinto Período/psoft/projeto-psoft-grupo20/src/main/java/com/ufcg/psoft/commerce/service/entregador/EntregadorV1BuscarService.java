package com.ufcg.psoft.commerce.service.entregador;

import com.ufcg.psoft.commerce.model.Entregador;
import com.ufcg.psoft.commerce.repository.EntregadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregadorV1BuscarService implements EntregadorBuscarService {
    @Autowired
    EntregadorRepository entregadorRepository;
    @Override
    public List<Entregador> buscarEntregadores() {
        return entregadorRepository.findAll();
    }
}