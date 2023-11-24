package com.ufcg.psoft.commerce.exception;

public class AssociacaoNaoExisteException extends PitsAException{
    public AssociacaoNaoExisteException(){
        super("A associacao consultada nao existe!");
    }
}