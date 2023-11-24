package com.ufcg.psoft.commerce.service.sabor;

import com.ufcg.psoft.commerce.exception.CodigoInvalidoException;
import com.ufcg.psoft.commerce.exception.EstabelecimentoNaoExisteException;
import com.ufcg.psoft.commerce.model.Estabelecimento;
import com.ufcg.psoft.commerce.model.Sabor;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import com.ufcg.psoft.commerce.repository.SaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaborV1BuscarByTipoServiceService implements SaborBuscarServiceByTipoService {
    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    SaborRepository saborRepository;

    public List<Sabor> buscar(Long estabelecimentoId, String estabelecimentoCodigoAcesso, String tipo) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(estabelecimentoId).orElseThrow(EstabelecimentoNaoExisteException::new);
        if(!estabelecimento.getCodigoAcesso().equals(estabelecimentoCodigoAcesso)){
            throw new CodigoInvalidoException();
        }
        List<Sabor> sabores = saborRepository.findByTipo(tipo);
        return sabores;
    }
}
