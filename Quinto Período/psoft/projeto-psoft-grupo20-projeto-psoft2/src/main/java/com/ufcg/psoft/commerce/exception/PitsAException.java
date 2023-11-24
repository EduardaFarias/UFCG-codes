package com.ufcg.psoft.commerce.exception;

public class PitsAException extends RuntimeException {
    public PitsAException(){
        super("Erro inesperado no PitsA");
    }

    public PitsAException(String message){
        super(message);
    }
}
