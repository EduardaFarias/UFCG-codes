package com.ufcg.psoft.commerce.service.entregador;

import com.ufcg.psoft.commerce.exception.ClienteNaoExisteException;
import com.ufcg.psoft.commerce.exception.CodigoInvalidoException;
import com.ufcg.psoft.commerce.exception.EntregadorNaoExisteExeption;
import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.model.Entregador;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import com.ufcg.psoft.commerce.repository.EntregadorRepository;
import com.ufcg.psoft.commerce.service.cliente.ClienteExcluirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntregadorV1ExcluirService implements EntregadorExcluirService {
    @Autowired
    EntregadorRepository entregadorRepository;
    @Override
    public void excluir(Long id, String codigoAcesso) {
        Entregador entregador = entregadorRepository.findById(id).orElseThrow(EntregadorNaoExisteExeption::new);
        if (!entregador.getCodigoAcesso().equals(codigoAcesso)){
            throw new CodigoInvalidoException();
        }
        entregadorRepository.delete(entregador);
    }
}
