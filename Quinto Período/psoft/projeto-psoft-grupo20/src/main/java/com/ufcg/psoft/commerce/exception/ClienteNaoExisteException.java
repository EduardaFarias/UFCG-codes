package com.ufcg.psoft.commerce.exception;

public class ClienteNaoExisteException extends PitsAException {
    public ClienteNaoExisteException() {
        super("O cliente consultado nao existe!");
    }
}
