package com.ufcg.psoft.commerce.exception;

public class SaborNaoDisponivelException extends PitsAException{
    public SaborNaoDisponivelException(){
        super("O sabor consultado nao esta disponivel!");
    }
}