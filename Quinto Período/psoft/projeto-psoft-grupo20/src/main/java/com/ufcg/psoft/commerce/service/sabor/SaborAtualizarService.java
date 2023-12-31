package com.ufcg.psoft.commerce.service.sabor;

import com.ufcg.psoft.commerce.dto.sabor.SaborPostPutRequestDTO;
import com.ufcg.psoft.commerce.model.Sabor;

@FunctionalInterface
public interface SaborAtualizarService {
    Sabor atualizar(Long saborId, Long estabelecimentoId, String estabelecimentoCodigoAcesso,
                    SaborPostPutRequestDTO saborPostPutRequestDTO);
}
