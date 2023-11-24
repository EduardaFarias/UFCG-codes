package com.ufcg.psoft.commerce.exception;

public class SaborNaoExisteException extends PitsAException{
    public SaborNaoExisteException(){
        super("O sabor consultado nao existe!");
    }
}