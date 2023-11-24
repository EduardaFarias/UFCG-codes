package com.ufcg.psoft.commerce.service.sabor;

import com.ufcg.psoft.commerce.exception.SaborNaoDisponivelException;
import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.model.Sabor;
import com.ufcg.psoft.commerce.repository.ClienteRepository;
import com.ufcg.psoft.commerce.repository.SaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaborV1NotificarClientesService implements SaborNotificarClienteService{
    @Autowired
    SaborRepository saborRepository;

    @Autowired
    ClienteRepository clienteRepository;
    @Override
    public List<String> notificarCliente(Long saborId) {
        Sabor sabor = saborRepository.findById(saborId).orElseThrow(SaborNaoDisponivelException::new);
        List<String> notificacoes = new ArrayList<>();
        if (sabor.isDisponivel()) {
            for (Cliente cliente : sabor.getClientesInteressados()) {
                if (clienteRepository.existsById(cliente.getId())) {
                    String notificacao = cliente.getNome() + ", o sabor " + sabor.getNome() + " está disponível!";
                    notificacoes.add(notificacao);
                    System.out.println(notificacao);
                }
                sabor.getClientesInteressados().remove(cliente);
            }
            saborRepository.save(sabor);
        }
        return notificacoes;
    }
}
