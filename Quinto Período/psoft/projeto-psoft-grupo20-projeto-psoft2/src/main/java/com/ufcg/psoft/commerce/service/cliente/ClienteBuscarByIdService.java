package com.ufcg.psoft.commerce.service.cliente;

import com.ufcg.psoft.commerce.model.Cliente;

@FunctionalInterface
public interface ClienteBuscarByIdService {
    public Cliente buscar(Long id);
}
