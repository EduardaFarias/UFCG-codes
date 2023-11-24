package com.ufcg.psoft.commerce.exception;

public class InteresseSaborNaoExisteException extends PitsAException {
    public InteresseSaborNaoExisteException() {
        super("O interesse consultado nao existe!");
    }
}