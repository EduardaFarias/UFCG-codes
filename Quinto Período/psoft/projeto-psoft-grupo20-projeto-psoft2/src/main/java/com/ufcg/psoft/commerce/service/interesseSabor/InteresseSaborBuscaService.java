package com.ufcg.psoft.commerce.service.interesseSabor;

import com.ufcg.psoft.commerce.dto.interesseSabor.InteresseSaborPostPutRequestDTO;
import com.ufcg.psoft.commerce.model.InteresseSabor;

import java.util.List;

@FunctionalInterface
public interface InteresseSaborBuscaService {
    public List<InteresseSabor> buscar();
}