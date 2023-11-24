package com.ufcg.psoft.commerce.exception;

public class CardapioNaoExisteException extends PitsAException{
    public CardapioNaoExisteException(){
        super("O cardápio consultado não existe!");
    }
}