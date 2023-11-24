package com.ufcg.psoft.commerce.service.estabelecimento;

import com.ufcg.psoft.commerce.exception.CodigoInvalidoException;
import com.ufcg.psoft.commerce.exception.EstabelecimentoNaoExisteException;
import com.ufcg.psoft.commerce.model.Estabelecimento;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstabelecimentoV1ExcluirService implements EstabelecimentoExcluirService{
    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;
    @Override
    public void excluir(Long id, String codigoAcesso) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(id).orElseThrow(EstabelecimentoNaoExisteException::new);
        if(!estabelecimento.getCodigoAcesso().equals(codigoAcesso)){
            throw new CodigoInvalidoException();
        }
        estabelecimentoRepository.delete(estabelecimento);
    }
}
