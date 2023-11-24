package com.ufcg.psoft.commerce.service.interesseSabor;

import com.ufcg.psoft.commerce.dto.interesseSabor.InteresseSaborPostPutRequestDTO;
import com.ufcg.psoft.commerce.exception.ClienteNaoExisteException;
import com.ufcg.psoft.commerce.exception.SaborJaDisponivelException;
import com.ufcg.psoft.commerce.exception.SaborNaoExisteException;
import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.model.InteresseSabor;
import com.ufcg.psoft.commerce.model.Sabor;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import com.ufcg.psoft.commerce.repository.InteresseSaborRepository;
import com.ufcg.psoft.commerce.repository.SaborRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InteresseSaborV1CriaService implements InteresseSaborCriaService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    InteresseSaborRepository interesseSaborRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    SaborRepository saborRepository;

    @Override
    public InteresseSabor criar(InteresseSaborPostPutRequestDTO interesseSaborPostPutRequestDTO) {
        Cliente cliente = clienteRepository.findById(interesseSaborPostPutRequestDTO.getClienteId()).orElseThrow(ClienteNaoExisteException::new);
        Sabor sabor = saborRepository.findById(interesseSaborPostPutRequestDTO.getSaborId()).orElseThrow(SaborNaoExisteException::new);

        if (sabor.isDisponivel()) {
            throw new SaborJaDisponivelException();
        }

        InteresseSabor interesseSabor = modelMapper.map(interesseSaborPostPutRequestDTO, InteresseSabor.class);
        interesseSabor.setClienteId(cliente.getId());
        interesseSabor.setSaborId(sabor.getId());

        return interesseSaborRepository.save(interesseSabor);
    }
}