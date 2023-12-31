package br.edu.ufcg.computacao.psoft.commerce.service.produtoService;

import br.edu.ufcg.computacao.psoft.commerce.model.Produto;

import java.util.List;

@FunctionalInterface
public interface ProdutoBuscarService {
    public List<Produto> buscar();
}
