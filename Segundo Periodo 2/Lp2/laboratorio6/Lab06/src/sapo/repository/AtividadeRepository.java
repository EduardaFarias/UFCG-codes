package sapo.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import sapo.entidades.Atividade;
import sapo.entidades.Pessoa;
import sapo.entidades.Tarefa;

/**
 * Entidade de repositório para os atividades
 * @author eduarda
 *
 */
public class AtividadeRepository {
	private HashMap<String, Atividade> atividades;
	private int atividadesCadastradas;
	
	/**
	 * Construtor padrão para inicialização das entidades básicas. 
	 */
	public AtividadeRepository() {
		this.atividades = new HashMap<>();
		this.atividadesCadastradas = 0;
	}
	/**
	 * Cadastra uma atividade no repositório
	 * Adiciona numa coleção de atividades o id dessa atividade e a atividade
	 * @param atividade atividade resultante desse cadastro.
	 * 
	 */
	public void cadastrarAtividade(Atividade atividade) {
		atividades.put(atividade.getIdAtividade(), atividade);
		atividadesCadastradas++;
	}
	/**
	 * Pega a posição da atividade cadastrada atéo momento
	 * @return o int referente a posição(ou quantidade de atividades cadastradas 
	 * até o momento)
	 */
	public int getPosicaoAtividades() {
		return this.atividadesCadastradas;
	}
	/**
	 * Pega uma atividade da coleção de atividades pelo id dessa atividade
	 * @param idAtividade
	 * @return uma atividade
	 */
	public Optional<Atividade> getAtividade(String idAtividade) {
		return Optional.ofNullable(this.atividades.get(idAtividade));
	}
	
	/**
	 * pega o total de atividades cadastradas
	 * @return o inteiro referente a quantidade de atividades
	 * cadastradas
	 */
	public int getTotalCadastrados() {
		return this.atividadesCadastradas;
	}

	/*
	 * Método que busca uma tarefa pelo nome e ordena pela mais recente retornando
	 * um array de string com as representações textuais das mesmas.
	 */
	public String[] buscaTarefasByNome(String nome) {
		ArrayList<Tarefa> tarefas = new ArrayList<>();
		for (Atividade atv : atividades.values()) {
			tarefas.addAll(atv.getTarefas());
		}
		return tarefas.stream().filter(t -> t.getNome().toLowerCase().equals(nome))
				.sorted((t1, t2) -> Integer.compare(t2.getOrdem(), t1.getOrdem())).toArray(String[]::new);
	}

	/*
	 * Método que busca uma tarefa pelo nome e pelo id de uma atividade e ordena
	 * pela mais recente retornando um array de string com as representações
	 * textuais das mesmas.
	 */
	public String[] buscaTarefasByNomeAndAtividade(String idAtividade, String nome) {
		return atividades.get(idAtividade).getTarefas().stream().filter(t -> t.getNome().toLowerCase().equals(nome))
				.sorted((t1, t2) -> Integer.compare(t2.getOrdem(), t1.getOrdem())).toArray(String[]::new);
	}

	/*
	 * Método que busca atividades pelas informações das atividades.
	 */
	public List<Atividade> buscaAtividades(String[] keyWords) {
		return atividades.values().stream().filter(atv -> atividadeContainsConsulta(keyWords, atv))
				.sorted(Comparator.comparing(Atividade::getNome)).collect(Collectors.toList());
	}

	/*
	 * Método que busca em atividade uma determinada consulta.
	 */
	private boolean atividadeContainsConsulta(String[] consulta, Atividade atividade) {
		for (String palavra : consulta) {
			if (!atividade.getStringBusca().toLowerCase().contains(palavra)) {
				return false;
			}
		}
		return true;
	}

	/*
	 * Método que retorna uma lista de tarefas ativas.
	 */
	public List<Tarefa> getTarefasAtivas(String idAtividade) {
		return atividades.get(idAtividade).getTarefas().stream().filter(t -> !t.isConcluida())
				.collect(Collectors.toList());
	}

	/*
	 * Método que retorna as tarefas ativas da atividade recebida, ordenadas das que
	 * têm mais habilidades em comum com a pessoa, até as que têm menos habilidades
	 * em caso de empate, das que não tem pessoas associadas primeiro, e, em caso de
	 * empate, a partir do código, em ordem alfabética.
	 */
	public String[] buscaSugestaoAtividade(Pessoa pessoa, String idAtividade) {
		return getTarefasAtivas(idAtividade).stream()
				.sorted(Comparator.comparing((Tarefa t) -> qtdHabilidadesEmComum(t, pessoa)).reversed()
						.thenComparing(t -> t.hasPessoa() ? 1 : -1).thenComparing(Tarefa::getIdTarefa))
				.toArray(String[]::new);
	}

	/*
	 * Método que retorna a quantidade de habilidades em comum entre uma pessoa e
	 * uma tarefa.
	 */
	public Integer qtdHabilidadesEmComum(Tarefa tarefa, Pessoa pessoa) {
		Set<String> intersecao = new HashSet<String>();
		for (String habi : tarefa.getHabilidades()) {
			if (Arrays.asList(pessoa.getHabilidades()).contains(habi)) {
				intersecao.add(habi);
			}
		}
		return intersecao.size();
	}
}