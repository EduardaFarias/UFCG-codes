package com.ufcg.psoft.commerce.service.interesseSabor;

import com.ufcg.psoft.commerce.dto.interesseSabor.InteresseSaborPostPutRequestDTO;
import com.ufcg.psoft.commerce.model.InteresseSabor;

@FunctionalInterface
public interface InteresseSaborCriaService {
    public InteresseSabor criar(InteresseSaborPostPutRequestDTO interesseSaborPostPutRequestDTO);
}