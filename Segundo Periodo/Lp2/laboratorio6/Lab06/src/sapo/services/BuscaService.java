package sapo.services;

import java.util.List;

import sapo.entidades.Atividade;
import sapo.entidades.Busca;
import sapo.entidades.Pessoa;
import sapo.repository.AtividadeRepository;
import sapo.repository.BuscaRepository;
import sapo.repository.PessoaRepository;

public class BuscaService {

	private PessoaRepository pr;
	private AtividadeRepository ar;
	private BuscaRepository br;

	public BuscaService(PessoaRepository pr, AtividadeRepository ar, BuscaRepository br) {
		this.pr = pr;
		this.ar = ar;
		this.br = br;
	}

	
	public String[] exibirPessoas(String consulta) {
		consulta = consulta.toLowerCase();
		String[] keyWords = consulta.split(" ");
		List<Pessoa> pessoas = pr.buscaPessoas(keyWords);
		String[] resultado = pessoas.stream().map(Pessoa::toString).toArray(String[]::new);
		Busca busca = new Busca("PESSOA", resultado);
		br.addBusca(busca);
		return resultado;
	}

	public String[] buscarAtividade(String consulta) {
		consulta = consulta.toLowerCase();
		String[] keyWords = consulta.split(" ");
		List<Atividade> atividades = ar.buscaAtividades(keyWords);
		String[] resultado = atividades.stream().map(Atividade::toString).toArray(String[]::new);
		Busca busca = new Busca("ATIVIDADE", resultado);
		br.addBusca(busca);
		return resultado;
	}

	public String[] buscarTarefas(String nome) {
		String[] resultado = ar.buscaTarefasByNome(nome.toLowerCase());
		Busca busca = new Busca("TAREFA", resultado);
		br.addBusca(busca);
		return resultado; 
	}
	
	public String[] buscarTarefas(String idAtividade, String nome) {
		String[] resultado = ar.buscaTarefasByNomeAndAtividade(idAtividade, nome.toLowerCase());
		Busca busca = new Busca("TAREFA", resultado);
		br.addBusca(busca);
		return resultado;
	}

	public String[] sugerirTarefas(String idAtividade, String cpf) {
		String[] resultado = ar.buscaSugestaoAtividade(pr.getPessoa(cpf), idAtividade);
		Busca busca = new Busca("SUGESTÃO", resultado);
		br.addBusca(busca);
		return resultado;
	}

	public String[] buscaMaisRecentes(int nBuscas) {
		return br.buscaMaisRecentes(nBuscas);
	}

	public String[] exibirHistoricoBusca(int indexBusca) {
		return br.exibirHistoricoBusca(indexBusca);
	}

}
