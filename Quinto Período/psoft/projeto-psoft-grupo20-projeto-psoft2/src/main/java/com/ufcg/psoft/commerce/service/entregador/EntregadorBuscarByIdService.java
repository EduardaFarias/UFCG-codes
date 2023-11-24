package com.ufcg.psoft.commerce.service.entregador;

import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.model.Entregador;

@FunctionalInterface
public interface EntregadorBuscarByIdService {
    public Entregador buscar(Long id);
}
