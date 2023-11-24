package com.ufcg.psoft.commerce.service.cliente;

import com.ufcg.psoft.commerce.model.Cliente;

import java.util.List;

@FunctionalInterface
public interface ClienteBuscarService {
    public List<Cliente> buscarClientes();
}
