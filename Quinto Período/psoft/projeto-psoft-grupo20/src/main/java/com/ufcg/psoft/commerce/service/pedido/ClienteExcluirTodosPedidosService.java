package com.ufcg.psoft.commerce.service.pedido;

@FunctionalInterface
public interface ClienteExcluirTodosPedidosService {
    void excluirTodosPedidos(Long clienteId,String codigoAcessoCliente);
}
