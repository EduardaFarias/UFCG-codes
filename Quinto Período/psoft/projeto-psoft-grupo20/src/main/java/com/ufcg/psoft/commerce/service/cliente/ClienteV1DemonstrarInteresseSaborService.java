package com.ufcg.psoft.commerce.service.cliente;

import com.ufcg.psoft.commerce.exception.ClienteNaoExisteException;
import com.ufcg.psoft.commerce.exception.CodigoInvalidoException;
import com.ufcg.psoft.commerce.exception.SaborJaDisponivelException;
import com.ufcg.psoft.commerce.exception.SaborNaoExisteException;
import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.model.Sabor;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import com.ufcg.psoft.commerce.repository.SaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteV1DemonstrarInteresseSaborService implements ClienteDemonstraInteressesSaborService{
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    SaborRepository saborRepository;
    @Override
    public Sabor demonstrarInteresse(Long clienteId, String clienteCodigoAcesso, Long saborId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(ClienteNaoExisteException::new);
        Sabor sabor = saborRepository.findById(saborId).orElseThrow(SaborNaoExisteException::new);

        if (!cliente.getCodigoAcesso().equals(clienteCodigoAcesso)){
            throw new CodigoInvalidoException();
        }

        if (sabor.isDisponivel()){
            throw new SaborJaDisponivelException();
        }

        sabor.getClientesInteressados().add(cliente);
        return saborRepository.save(sabor);
    }
}
