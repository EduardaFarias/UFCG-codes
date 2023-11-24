package com.ufcg.psoft.commerce.exception;

public class PedidoNaoExisteException extends PitsAException{
    public PedidoNaoExisteException(){
        super("O pedido consultado nao existe!");
    }
}
