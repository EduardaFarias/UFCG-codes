package com.ufcg.psoft.commerce.exception;

public class PizzaTamanhoInvalidoException extends PitsAException{
    public PizzaTamanhoInvalidoException(){
        super("Quantidade de sabores invalida, pizzas medias so podem ter 1 sabor!");
    }
}