package com.ufcg.psoft.commerce.repository;

import com.ufcg.psoft.commerce.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByClienteIdAndId(Long clienteId, Long pedidoId);
    List<Pedido> findAllByClienteId(Long clienteId);

    List<Pedido> findAllByEstabelecimentoId(Long estabelecimentoId);

    List<Pedido> findAllByEstabelecimentoIdAndId(Long estabelecimentoId, Long pedidoId);

    List<Pedido> findByClienteIdAndEstabelecimentoId(Long clienteId, Long estabelecimentoId);

    List<Pedido> findAllByClienteIdAndEstabelecimentoId(Long clienteId, Long estabelecimentoId);

    List<Pedido> findAllByClienteIdAndEstabelecimentoIdAndAndStatusEntrega(Long clienteId, Long estabelecimentoId, String statusEntrega);

    void deleteAllByClienteId(Long clienteId);

    void deleteAllByEstabelecimentoId(Long estabelecimentoId);
}
