package br.edu.ufcg.computacao.psoft.commerce.service.pessoaService;
import br.edu.ufcg.computacao.psoft.commerce.dto.pessoaDTO.PessoaPostPutDTO;
import br.edu.ufcg.computacao.psoft.commerce.model.Pessoa;
import br.edu.ufcg.computacao.psoft.commerce.repository.pessoaRepository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaV1ExcluirService implements PessoaExcluirService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Override
    public void excluir(Long id) {
        pessoaRepository.excluir(id);
    }
}



