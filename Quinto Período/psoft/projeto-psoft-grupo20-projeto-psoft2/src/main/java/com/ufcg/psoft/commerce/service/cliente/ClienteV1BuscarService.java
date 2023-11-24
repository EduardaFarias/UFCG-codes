package com.ufcg.psoft.commerce.service.cliente;

import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteV1BuscarService implements ClienteBuscarService{
    @Autowired
    ClienteRepository clienteRepository;
    @Override
    public List<Cliente> buscarClientes() {
        return clienteRepository.findAll();
    }
}
