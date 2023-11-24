package com.ufcg.psoft.commerce.service.sabor;

import java.util.List;

@FunctionalInterface
public interface SaborNotificarClienteService {
    List<String> notificarCliente(Long saborId);
}
