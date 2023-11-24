package com.ufcg.psoft.commerce.service.entregador;

import com.ufcg.psoft.commerce.exception.ClienteNaoExisteException;
import com.ufcg.psoft.commerce.exception.EntregadorNaoExisteExeption;
import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.model.Entregador;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import com.ufcg.psoft.commerce.repository.EntregadorRepository;
import com.ufcg.psoft.commerce.service.cliente.ClienteBuscarByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntregadorV1BuscarByIdService implements EntregadorBuscarByIdService {
    @Autowired
    EntregadorRepository entregadorRepository;

    @Override
    public Entregador buscar(Long id) {
        Entregador entregador = entregadorRepository.findById(id).orElseThrow(EntregadorNaoExisteExeption::new);
        return entregador;
    }
}
