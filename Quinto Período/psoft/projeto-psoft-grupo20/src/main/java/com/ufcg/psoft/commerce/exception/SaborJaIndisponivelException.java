package com.ufcg.psoft.commerce.exception;

public class SaborJaIndisponivelException extends PitsAException{
    public SaborJaIndisponivelException(){
        super("O sabor consultado ja esta indisponivel!");
    }
}