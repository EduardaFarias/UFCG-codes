package com.ufcg.psoft.commerce.service.interesseSabor;

import com.ufcg.psoft.commerce.model.InteresseSabor;
import com.ufcg.psoft.commerce.repository.InteresseSaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteresseSaborV1BuscaService implements InteresseSaborBuscaService {

    @Autowired
    InteresseSaborRepository interesseSaborRepository;

    public List<InteresseSabor> buscar() {
        return interesseSaborRepository.findAll();
    }

}
