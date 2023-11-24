package com.ufcg.psoft.commerce.exception;

public class PedidoStatusInvalidoException extends PitsAException{
    public PedidoStatusInvalidoException(){
        super("O status do pedido e invalido!");
    }
}
