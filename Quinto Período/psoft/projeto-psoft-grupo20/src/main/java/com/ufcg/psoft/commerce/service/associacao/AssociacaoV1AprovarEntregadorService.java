package com.ufcg.psoft.commerce.service.associacao;

import com.ufcg.psoft.commerce.exception.AssociacaoNaoExisteException;
import com.ufcg.psoft.commerce.exception.CodigoInvalidoException;
import com.ufcg.psoft.commerce.exception.EntregadorNaoExisteExeption;
import com.ufcg.psoft.commerce.exception.EstabelecimentoNaoExisteException;
import com.ufcg.psoft.commerce.model.Associacao;
import com.ufcg.psoft.commerce.model.Entregador;
import com.ufcg.psoft.commerce.model.Estabelecimento;
import com.ufcg.psoft.commerce.repository.AssociacaoRepository;
import com.ufcg.psoft.commerce.repository.EntregadorRepository;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociacaoV1AprovarEntregadorService implements AssociacaoAprovarEntregadorService {
    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    EntregadorRepository entregadorRepository;

    @Autowired
    AssociacaoRepository associacaoRepository;
    @Override
    public Associacao aprovarEntregador(Long entregadorId, Long estabelecimentoId, String codigoAcessoEstabelecimento) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(estabelecimentoId).orElseThrow(EstabelecimentoNaoExisteException::new);
        entregadorRepository.findById(entregadorId).orElseThrow(EntregadorNaoExisteExeption::new);
        Associacao associacao = associacaoRepository.findByEntregadorIdAndEstabelecimentoId(entregadorId, estabelecimentoId)
                .orElseThrow(AssociacaoNaoExisteException::new);

        if (!estabelecimento.getCodigoAcesso().equals(codigoAcessoEstabelecimento)){
            throw new CodigoInvalidoException();
        }

        associacao.setStatus(true);
        return associacaoRepository.save(associacao);
    }
}
