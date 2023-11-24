package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.dto.pedido.PedidoPostPutRequestDTO;
import com.ufcg.psoft.commerce.exception.CodigoInvalidoException;
import com.ufcg.psoft.commerce.exception.EstabelecimentoNaoExisteException;
import com.ufcg.psoft.commerce.exception.PedidoNaoExisteException;
import com.ufcg.psoft.commerce.exception.PedidoStatusInvalidoException;
import com.ufcg.psoft.commerce.model.Estabelecimento;
import com.ufcg.psoft.commerce.model.Pedido;
import com.ufcg.psoft.commerce.repository.EstabelecimentoRepository;
import com.ufcg.psoft.commerce.repository.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstabelecimentoV1AprontarPedidoService implements EstabelecimentoAprontaPedidoService{
    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    FuncionarioNotificaProdutoProntoService funcionarioNotificaProdutoProntoService;
    @Override
    public Pedido aprontaPedidoService(Long pedidoId, Long estabelecimentoId, String estabelecimentoCodigoAcesso, PedidoPostPutRequestDTO pedidoPostPutRequestDTO) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(estabelecimentoId).orElseThrow(EstabelecimentoNaoExisteException::new);
        if (!estabelecimento.getCodigoAcesso().equals(estabelecimentoCodigoAcesso)) {
            throw new CodigoInvalidoException();
        }

        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(PedidoNaoExisteException::new);

        if (!pedido.getStatusEntrega().equals("Pedido em preparo")) {
            throw new PedidoStatusInvalidoException();
        }
        pedido.setStatusEntrega("Pedido pronto");
        modelMapper.map(pedidoPostPutRequestDTO, pedido);
        funcionarioNotificaProdutoProntoService.funcionarioNotificaPedidoPronto(pedidoId);
        return pedidoRepository.save(pedido);
    }
}
