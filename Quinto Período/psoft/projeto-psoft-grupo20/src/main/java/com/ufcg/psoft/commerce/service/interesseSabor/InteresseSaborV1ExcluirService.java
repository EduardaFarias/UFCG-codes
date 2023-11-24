package com.ufcg.psoft.commerce.service.interesseSabor;

import com.ufcg.psoft.commerce.exception.ClienteNaoExisteException;
import com.ufcg.psoft.commerce.exception.CodigoInvalidoException;
import com.ufcg.psoft.commerce.exception.EstabelecimentoNaoExisteException;
import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.model.InteresseSabor;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import com.ufcg.psoft.commerce.repository.InteresseSaborRepository;
import com.ufcg.psoft.commerce.repository.SaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InteresseSaborV1ExcluirService implements InteresseSaborExcluirService {
    @Autowired
    InteresseSaborRepository interesseSaborRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    SaborRepository saborRepository;

    public void excluir(Long interesseSaborId) {
        InteresseSabor interesseSabor = interesseSaborRepository.findById(interesseSaborId).orElseThrow(EstabelecimentoNaoExisteException::new);
        interesseSaborRepository.delete(interesseSabor);
    }
}