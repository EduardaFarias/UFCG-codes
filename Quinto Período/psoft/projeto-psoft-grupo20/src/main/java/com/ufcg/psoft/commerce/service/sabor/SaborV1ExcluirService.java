package com.ufcg.psoft.commerce.service.sabor;

import com.ufcg.psoft.commerce.exception.CodigoInvalidoException;
import com.ufcg.psoft.commerce.exception.EstabelecimentoNaoExisteException;
import com.ufcg.psoft.commerce.exception.SaborNaoExisteException;
import com.ufcg.psoft.commerce.model.Estabelecimento;
import com.ufcg.psoft.commerce.model.Sabor;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import com.ufcg.psoft.commerce.repository.SaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaborV1ExcluirService implements SaborExcluirService{
    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    SaborRepository saborRepository;
    @Override
    public void excluirSabor(Long saborId, Long estabelecimentoId, String estabelecimentoCodigoAcesso) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(estabelecimentoId).orElseThrow(EstabelecimentoNaoExisteException::new);
        if(!estabelecimento.getCodigoAcesso().equals(estabelecimentoCodigoAcesso)){
            throw new CodigoInvalidoException();
        }
        Sabor sabor = saborRepository.findById(saborId).orElseThrow(SaborNaoExisteException::new);
        saborRepository.delete(sabor);
    }
}
