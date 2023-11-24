package com.ufcg.psoft.commerce.service.cliente;

import com.ufcg.psoft.commerce.model.Sabor;

@FunctionalInterface
public interface ClienteDemonstraInteressesSaborService {
   Sabor demonstrarInteresse(Long clienteId, String clienteCodigoAcesso, Long saborId);
}
