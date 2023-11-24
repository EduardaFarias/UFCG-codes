package com.ufcg.psoft.commerce.service.sabor;

import com.ufcg.psoft.commerce.exception.*;
import com.ufcg.psoft.commerce.model.Estabelecimento;
import com.ufcg.psoft.commerce.model.Sabor;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import com.ufcg.psoft.commerce.repository.SaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaborV1AtualizarDisponibilidadeService implements SaborAtualizarDisponibilidadeService{

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    SaborRepository saborRepository;
    @Override
    public Sabor atualizarDisponibilidade(Long saborId, boolean disponibilidade, Long estabelecimentoId, String estabelecimentoCodigoAcesso) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(estabelecimentoId).orElseThrow(EstabelecimentoNaoExisteException::new);
        if(!estabelecimento.getCodigoAcesso().equals(estabelecimentoCodigoAcesso)){
            throw new CodigoInvalidoException();
        }
        Sabor sabor = saborRepository.findById(saborId).orElseThrow(SaborNaoExisteException::new);

        if (disponibilidade){
            if (sabor.isDisponivel()) {
                throw new SaborJaDisponivelException();
            }

        }else {
                if (!sabor.isDisponivel()){
                    throw new SaborJaIndisponivelException();
                }
            }
       sabor.setDisponivel(disponibilidade);
        return saborRepository.save(sabor);
    }}

