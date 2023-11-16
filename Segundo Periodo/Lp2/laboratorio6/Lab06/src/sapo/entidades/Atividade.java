package sapo.entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * Atividade respresenta um conjunto de ações de ações(tarefas)
 * que devem ser tomadas para atingir um determinado objetivo,
 * fazer a atividade
 * @author eduarda
 *
 */
public class Atividade {
	private String nome;
	private String descricao;
	private String cpfResponsavel;
	private String idAtividade;
	private Pessoa pessoa;
	private boolean aberta;
	private boolean encerrada;
	private boolean desativada;
	private Map<String, Tarefa> tarefas;
	private int totalAtividadesCadastradas = 0;

	/**
	 * Constrói uma atividade a partir do nome da atividade, descricao da atividade
	 * um objeto pessoa do tipo pessoa e o id da atividade 
	 * @param nome
	 * @param descricao
	 * @param pessoa
	 * @param idAtividade
	 */
	public Atividade(String nome, String descricao, Pessoa pessoa, String idAtividade) {
		this.nome = nome;
		this.descricao = descricao;
		this.pessoa = pessoa;
		this.idAtividade = idAtividade;
		this.encerrada = false;
		this.desativada = false;
		this.aberta = true;
		this.tarefas = new HashMap<String, Tarefa>();
	}
	
	/**
	 * Representação textual de uma atividade, formada pelo id da atividade
	 * nome da atividade seu responsável(nome e cpf do responsavel pela atividade)
	 * descricao dessa atividade, quantidade de tarefas pendentes e tarefas cadastradas,
	 * seguidas pelas três primeiras tarefas pendentes.
	 * A ultima linha de mensagem seria uma linha em branco, mas seguindo o exemplo
	 * da exibição no documento do Lab06 optei pot tirar esse "\n" após ser exibida a 
	 * ultima das três atividades pendentes
	 * @author eduarda
	 */
	@Override
	public String toString() {
		List<Tarefa> tarefas = new ArrayList<Tarefa>(this.tarefas.values());
		List<Tarefa> tarefasPendentes = new ArrayList<Tarefa>();
		for (Tarefa tarefa : tarefas) {
			if (!tarefa.isConcluida()) {
				tarefasPendentes.add(tarefa);
			}
			if (tarefasPendentes.size() == 3) {
				break;
			}
		}
		String mensagem = this.idAtividade + ": " + this.nome + "\n" 
						+ "Responsável: " + this.pessoa.getNome() + this.pessoa.getCpf() 
						+ "\n" 
						+ "===" 
						+ "\n" 
						+ this.descricao 
						+ "\n" 
						+ "===" 
						+ "\n" 
						+ "Tarefas: "+ tarefasPendentes.size() + "/" + tarefas.size() + "\n";

		for (Tarefa tarefa : tarefasPendentes) {
			if (tarefa != null) {
				mensagem += "- " + tarefa + "/n";
			}
		}
		mensagem = mensagem.substring(0, mensagem.length() - 1);
		return mensagem;
	}

	public String getStringBusca() {
		String resultado = this.nome + this.descricao + this.idAtividade;
		return resultado;
	}

	/**
	 * Pega o cpf da pessoa referenciada pelo this
	 * 
	 * @return cpfResponsavel o cpf do responsavel pela atividade
	 */
	public String getCpf() {
		return this.cpfResponsavel;
	}

	/**
	 * Pega o nome da atividade referenciada pelo this
	 * @return nome da atividade
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Pega o id da atividade referenciada
	 * @return idAtividade id da atividade
	 */
	public String getIdAtividade() {
		return this.idAtividade;
	}
	
	/**
	 * Toda atividade possui uma descrição que pode ser alterada
	 * @param descricao descricao da atividade
	 * @return não possui retorno
	 */
	public void alterarDescricaoAtividade(String descricao) {
		this.descricao = descricao;
	}
	
	/**
	 * Toda atividade possui um responsável que pode ser alterado,
	 * um novo responsável é uma pessoa nova, logo o cpf será diferente
	 * @param novoCpfResponsavel
	 * @return o método não possui retorno
	 */
	public void alterarResponsavelAtividade(String novoCpfResponsavel) {
		this.cpfResponsavel = novoCpfResponsavel;
	}
	
	/**
	 * Encerra uma atividade, porém uma atividade só pode ser encerrada
	 * se todas as suas tarefas estiverem concluídas por isso, acontece
	 * uma verificação em cada tarefa para ver se foi concluída
	 */
	public void encerrarAtividade() {
		List<Tarefa> tarefas = new ArrayList<Tarefa>(this.tarefas.values());
		int cont = 0;
		if(tarefas.size() == 0) {
			encerrada = true;
			aberta = false;
		}
		
		for (Tarefa tarefa : tarefas) {
			if (!tarefa.isConcluida()) {
				cont++;

			}

			if (tarefas.size() == cont) {
				encerrada = true;
				aberta = false;
			} else {
				throw new IllegalStateException("Para encerrar uma atividade todas as tarefas devem estar concluidas");
			}
		}
	}

	public boolean isAtividadeEncerrada() {
		return this.encerrada;
	}
	/**
	 * Desativa uma atividade, porém, uma atividade só pode
	 * ser desativada se todas as suas tarefas estiverem concluidas
	 */
	public void desativarAtividade() {
		List<Tarefa> tarefas = new ArrayList<Tarefa>(this.tarefas.values());
		int cont = 0;
		if (tarefas.size() == 0) {
			desativada = true;
			aberta = false;
		}
		for (Tarefa tarefa : tarefas) {
			if (!tarefa.isConcluida()) {
				cont++;
			}
			if (tarefas.size() == cont) {
				desativada = true;
				aberta = false;
			} else {
				throw new IllegalStateException("Para desativar uma atividade todas as tarefas devem estar concluidas");
			}
		}

	}

	public boolean isAtividadeDesativada() {
		return this.desativada;
	}
	/**
	 * Reabre uma atividade, uma atividade desativada ou encerrada não
	 * pode receber novas tarefas, mas pode ser reaberta, podendo assim,
	 * receber novas tarefas
	 */
	public void reabrirAtividade() {
		desativada = false;
		aberta = true;
	}
	public boolean isAtividadeReaberta() {
		return this.aberta;
	}
	
	/**
	 * Cadastra uma tarefa 
	 * @param nome nome da tarefa
	 * @param habilidades habilidades recomendadas
	 * Gera o id de tarefa e guarda em um HashMap o seu id 
	 * e a tarefa associada a este id
	 * @return o método não possui retorno
	 */
	public String cadastrarTarefa(String nome, String[] habilidades) {
		Tarefa tarefa = new Tarefa(nome, habilidades);
		String idTarefa = this.idAtividade + "-" + this.totalAtividadesCadastradas;
		tarefas.put(idTarefa, tarefa);
		totalAtividadesCadastradas++;
		return idTarefa;
	}
	
	/**
	 * Remove uma tarefa pelo id
	 * @param idTarefa id da tarefa
	 * o método não possui retorno
	 */
	public void removerTarefa(String idTarefa) {
		this.tarefas.remove(idTarefa);
	}

	/**
	 * Pega uma tarefa pelo id
	 * @param idTarefa id da tarefa
	 * @return Tarefa um objeto do tipo Tarefa
	 * obtido da coleção de tarefas
	 */
	public Tarefa getTarefa(String idTarefa) {
		return this.tarefas.get(idTarefa);
	}
	/**
	 * Pega o total de tarefas
	 * @param tarefas uma coleção de Tarefas, uma HashMap
	 * possuindo como chave o id de tarefa e como valor
	 * a a tarefa associada a essa chave
	 * @return
	 */
	public int getTotalTarefas(HashMap<String, Tarefa> tarefas) {
		return tarefas.size();
	}
	
	/**
	 * Pega todas as tarefas de uma coleção de tarefas
	 * @return cada tarefa da coleção de tarefas
	 */
	public Set<Tarefa> getTarefas() {
		return this.tarefas.values().stream().collect(Collectors.toSet());
	}
	
}
