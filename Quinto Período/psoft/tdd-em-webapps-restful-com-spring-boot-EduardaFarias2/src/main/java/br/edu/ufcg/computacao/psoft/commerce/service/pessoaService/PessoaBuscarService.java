package br.edu.ufcg.computacao.psoft.commerce.service.pessoaService;


import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;


import java.util.List;

@FunctionalInterface
public interface
PessoaBuscarService {
    public List<Pessoa> buscar();

}

