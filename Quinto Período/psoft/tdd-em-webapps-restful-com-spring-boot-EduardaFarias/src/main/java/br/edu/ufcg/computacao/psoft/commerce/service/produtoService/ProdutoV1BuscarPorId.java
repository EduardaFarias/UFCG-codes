package br.edu.ufcg.computacao.psoft.commerce.service.produtoService;

import br.edu.ufcg.computacao.psoft.commerce.model.Produto;
import br.edu.ufcg.computacao.psoft.commerce.repository.produtoRepository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoV1BuscarPorId implements ProdutoBuscarPorIdService{
    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto buscarProdutoPorId(Long id) {
        return produtoRepository.buscarProdutoPorId(id);
    }
}
