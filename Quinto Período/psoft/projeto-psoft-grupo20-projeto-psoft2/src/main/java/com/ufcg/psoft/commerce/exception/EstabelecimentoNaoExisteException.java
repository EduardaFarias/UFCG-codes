package com.ufcg.psoft.commerce.exception;

public class EstabelecimentoNaoExisteException extends PitsAException{
    public EstabelecimentoNaoExisteException(){
        super("O estabelecimento consultado nao existe!");
    }
}
