package com.ufcg.psoft.commerce.service.cliente;

import com.ufcg.psoft.commerce.exception.ClienteNaoExisteException;
import com.ufcg.psoft.commerce.exception.CodigoInvalidoException;
import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteV1ExcluirService implements ClienteExcluirService{
    @Autowired
    ClienteRepository clienteRepository;
    @Override
    public void excluir(Long id, String codigoAcesso) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(ClienteNaoExisteException::new);
        if (!cliente.getCodigoAcesso().equals(codigoAcesso)){
            throw new CodigoInvalidoException();
        }
        clienteRepository.delete(cliente);
    }
}
