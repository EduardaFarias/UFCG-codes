package com.ufcg.psoft.commerce.exception;

public class SaborJaDisponivelException extends PitsAException{
    public SaborJaDisponivelException(){
        super("O sabor consultado ja esta disponivel!");
    }
}