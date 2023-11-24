package br.edu.ufcg.computacao.psoft.commerce.service.produtoService;

import br.edu.ufcg.computacao.psoft.commerce.model.Produto;
import br.edu.ufcg.computacao.psoft.commerce.repository.produtoRepository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoV1BuscarService implements ProdutoBuscarService{
    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public List<Produto> buscar() {
        return produtoRepository.buscarProdutos();
    }
}
