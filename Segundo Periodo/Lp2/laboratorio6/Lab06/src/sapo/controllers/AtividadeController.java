package sapo.controllers;

import sapo.services.AtividadeService;

import java.util.HashMap;

import sapo.entidades.Atividade;
import sapo.entidades.Tarefa;
/**
 * O AtividadeController é responsável por receber requisições
 * do usuário e do sistema, e converter em operações lógicas(AtyividadeService).
 * A responsabilidade de validação das entradas é também operacionalizada aqui.
 * 
 * @author eduarda
 */
public class AtividadeController {
	
	private AtividadeService as;
	/**
	 * Construtor padrão de AtividadeController.
	 * @param as AtividadeService a ser utlizado pelo controller.
	 */
	public AtividadeController(AtividadeService as) {
		this.as = as;
	}
	/**
	 * Cadastra uma atividade, validando o nome, descricao e o cpf do responsavel 
	 * pela atividade
	 * @param nome Nome da atividade a ser cadastrada
	 * @param descricao Descrição da atividade a ser cadastrada
	 * @param cpfResponsavel Cpf da pessoa responsavel pela atividade
	 * 
	 */
	public String cadastrarAtividade(String nome,String descricao,String cpfResponsavel) {
		if (nome == null || nome.isBlank()) {
			throw new IllegalArgumentException("nome esta vazio");
		}
		if (descricao== null || descricao.isBlank()) {
			throw new IllegalArgumentException("descrição esta vazia");
		}
		if (cpfResponsavel == null || cpfResponsavel.isBlank()) {
			throw new IllegalArgumentException("cpf esta vazio");
		}
		return this.as.cadastrarAtividade(nome, descricao, cpfResponsavel);
	}
	/**
	 * Encerra uma atividade se todas as tarefas dessa atividade
	 * foram concluídas.
	 * 
	 * @param atividadeId Id da atividade a ser desativada
	 */
	public void encerrarAtividade(String atividadeId) {
		this.as.encerrarAtividade(atividadeId);
		
	}
	/**
	 * Desativa uma atividade se todas as tarefas dessa atividade 
	 * foram concluidas.
	 * 
	 * @param atividadeId Id da atividade a ser desativada
	 */
	public void desativarAtividade(String atividadeId) {
		this.as.desativarAtividade(atividadeId);
		
	}
	/**
	 * Uma atividade encerrada ou desativada pode ser reaberta
	 * 
	 * @param atividadeId Id da atividade a cer reaberta
	 */
	public void reabrirAtividade(String atividadeId) {
		this.as.reabrirAtividade(atividadeId);
	}
	/**
	 * Exibe uma atividade, a partir de seu ID
	 * @param atividadeId Id da atividade a ser exibida
	 * @return representação textual de uma ativade
	 */
	public String exibirAtividade(String atividadeId) {
		return this.as.exibirAtividade(atividadeId);
		
	}
	
	/**
	 * Toda atividade possui uma descrição, é possível alterar a 
	 * descriçao de uma atividade, pegando a atividade pelo id 
	 * 
	 * @param atividadeId id da atividade a ter a descrição alterada
	 * @param descricao nova descrição da atividade 
	 */
	public void alterarDescricaoAtividade(String atividadeId, String descricao) {
		this.as.alterarDescricaoAtividade(atividadeId, descricao);
		
	}
	/**
	 * Toda atividade possui um responsavel,é possivel alterar o responsavel
	 * de uma atividade
	 * @param atividadeId id da atividade a ter o responsavel alterado
	 * @param cpf novo cpf que representa o novo responsavel pela atividade
	 */
	public void alterarResponsavelAtividade(String atividadeId, String cpf) {
		this.as.alterarResponsavelAtividade(atividadeId, cpf);
		
	}
	/**
	 * Pega o id da atividade
	 * @return atividadeId O id da atividade
	 */
	public String getIdAtividade() {
		return this.as.getIdAtividade();
	}
	/**
	 * Cadastra uma tarefa 
	 * @param nome Nome da tarefa a ser cadastrada
	 * @param atividadeId id da atividade a qual pertence a tarefa
	 * @param habilidades Um array de String das habilidades recomendadas
	 */
	public void cadastrarTarefa(String nome, String atividadeId, String[] habilidades) {
		this.as.cadastrarTarefa(nome, atividadeId, habilidades);
	}
	
	/**
	 * pega uma tarefa da um determinada atividade
	 * @param atividadeId Id da atividade a qual a tarefa peretnce
	 * @return	A tarefa daquela atividade
	 */
	public Tarefa getTarefa(String idTarefa) {
		return this.as.getTarefa( idTarefa);
	}
	/**
	 * Recebe uma coleção de tarefas e retorna o seu total de tarefas
	 * @param tarefas Uma coleção de tarefas 
	 * @return a quantidade de tarefas da coleção
	 */
	public int getTotalTarefas(HashMap<String, Tarefa> tarefas) {
		return this.as.getTotalTarefas(tarefas);
	}
	/**
	 * Remove uma tarefa de uma atividade
	 * @param atividadeId Id da atividade em que vai ser retirada essa tarefa
	 */
	public void removerTarefa(String atividadeId) {
		this.as.removerTarefa(atividadeId);
	}
	

}


