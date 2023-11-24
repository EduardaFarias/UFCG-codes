package com.ufcg.psoft.commerce.service.cliente;

import com.ufcg.psoft.commerce.dto.cliente.ClientePostPutRequestDTO;
import com.ufcg.psoft.commerce.exception.ClienteNaoExisteException;
import com.ufcg.psoft.commerce.exception.CodigoInvalidoException;
import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteV1AtualizarService implements ClienteAtualizarService{
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public Cliente atualizar(Long id, String codigoAcesso, ClientePostPutRequestDTO clientePostPutRequestDTO) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(ClienteNaoExisteException::new);
        if(!cliente.getCodigoAcesso().equals(codigoAcesso)){
            throw new CodigoInvalidoException();
        }
        modelMapper.map(clientePostPutRequestDTO, cliente);
        return clienteRepository.save(cliente);
    }
}