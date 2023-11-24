package com.ufcg.psoft.commerce.service.sabor;

import com.ufcg.psoft.commerce.dto.sabor.SaborPostPutRequestDTO;
import com.ufcg.psoft.commerce.model.Sabor;
import org.springframework.stereotype.Service;

@FunctionalInterface
public interface SaborCriarService {
    Sabor criarSabor(SaborPostPutRequestDTO saborPostPutRequestDTO, Long estabelecimentoId, String estabelecimentoCodigoAcesso);

}
