package br.edu.ufcg.computacao.psoft.commerce.service.produtoService;

import br.edu.ufcg.computacao.psoft.commerce.dto.produtoDTO.ProdutoPostPutDTO;
import br.edu.ufcg.computacao.psoft.commerce.exception.CodigoDeBarrasInvalidoException;
import br.edu.ufcg.computacao.psoft.commerce.exception.PrecoAbaixoIgualZeroException;
import br.edu.ufcg.computacao.psoft.commerce.model.Produto;
import br.edu.ufcg.computacao.psoft.commerce.repository.produtoRepository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoV1CriarService implements ProdutoCriarService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoValidarCodigoService produtoValidarCodigoService;

    @Autowired
    private ProdutoValidarPrecoService produtoValidarPrecoService;
    @Override
    public Produto criar(ProdutoPostPutDTO produtoPostPutDTO) {
        String codigoBarras = produtoPostPutDTO.getCodigoBarras();
        Double preco = produtoPostPutDTO.getValor();
        if (!produtoValidarCodigoService.validarCodigo(codigoBarras)) {
            throw new CodigoDeBarrasInvalidoException();
        }
        if(!produtoValidarPrecoService.validarPreco(preco)){
            throw new PrecoAbaixoIgualZeroException();
        }
        return produtoRepository.criar(
                Produto.builder()
                        .nomeProduto(produtoPostPutDTO.getNomeProduto())
                        .codigoBarras(produtoPostPutDTO.getCodigoBarras())
                        .valor(produtoPostPutDTO.getValor())
                        .nomeFabricante(produtoPostPutDTO.getNomeFabricante())
                        .build());
    }



}
