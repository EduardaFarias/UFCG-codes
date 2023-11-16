package sapo.services;

import java.util.HashMap;

import sapo.entidades.Atividade;
import sapo.repository.AtividadeRepository;
import sapo.entidades.Pessoa;
import sapo.entidades.Tarefa;
/**
 * AtividadeService é a base de lógica de operações sobre a atividade.
 * Deamias classes devem fazer uso desse serviço para acessar e operar
 * sobre atividades.
 * @author eduarda
 *
 */
public class AtividadeService {
	/**
	 * Repositório. Configurado externamente para que essa classe não
	 * se responsabilize por sua inicializaçõ
	 */
	private AtividadeRepository ar;
	private PessoaService ps;
	private String nome;
	private String cpfResponsavel;
	
	/**
	 * Construtor padrão, recebendo o PessoaServicce para realizar
	 * as operações de pessoa
	 * @param ps PessoaService a ser utlizado pelo AtividadeServie
	 */
	public AtividadeService(PessoaService ps) {
		this.ar = new AtividadeRepository();
		this.ps = ps;
	}
	
	/**
	 * Cadastra uma atividade e gera o id dessa atividader durante seu cadastramento
	 * O id é composto pelas três primeiras vogais no nome da atividade e um número
	 * que representa a quantidade de atividades cadastradas até o momento, contudo,
	 * caso o nome da atividade não possua as três consoantes necessárias a consoante
	 * será substituida por um "X".
	 * @param nome Nome da atividade
	 * @param descricao Descrição da atividade
	 * @param cpfResponsavel Cpf do responsavel pela atividade
	 * @return o id dessa atividade
	 */
	public String cadastrarAtividade(String nome, String descricao, String cpfResponsavel) {
		this.nome = nome;
		this.cpfResponsavel = cpfResponsavel;
		Pessoa pessoa = this.ps.getPessoa(cpfResponsavel);
		String id = "";
		for (int i = 0; i < 5; i++) {
			char letra = Character.toUpperCase(nome.charAt(i));
			if (letra == 'A' || letra == 'E' || letra == 'I' || letra == 'O' || letra == 'U' || letra == ' ') {
				continue;
			} else
				id += letra;
		}
		for (int i = 0; i < id.length(); i++) {
			if (id.length() < 3) {
				id += "X";
			}
		}
		
		String idFinal = id + "-" + (this.ar.getPosicaoAtividades());
		this.ar.cadastrarAtividade(new Atividade(nome, descricao, pessoa, idFinal));
		return idFinal;
	}
	/**
	 * Uma atividade não pode ser removida,mas pode ser encerrada(concluída),
	 * porém, só é possível encerrar ou desativar atividades sem tarefas pendentes
	 * @param atividadeId Id da atividade que vai ser encerrada
	 */
	public void encerrarAtividade(String atividadeId) {
		Atividade atividade = this.ar.getAtividade(atividadeId).orElseThrow();
		atividade.encerrarAtividade();
		}
	
	/**
	 * Uma atividade pode ser desativada(abandonada ou invalidada),
	 * porém, só é possível encerrar ou desativar atividades sem tarefas pendentes
	 * @param atividadeId Id da atividade que vai ser desativada.
	 */
	public void desativarAtividade(String atividadeId) {
		Atividade atividade = this.ar.getAtividade(atividadeId).orElseThrow();
		if (atividade.isAtividadeDesativada()) {
			throw new IllegalStateException();
		}
		atividade.desativarAtividade();
	}
	/**
	 * Uma atividade encerrada ou desativada não pode receber tarefas
	 * entretanto, essa atividade pode ser reaberta.
	 * @param atividadeId id da atividade que vai ser reaberta
	 */
	public void reabrirAtividade(String atividadeId) {
		Atividade atividade = this.ar.getAtividade(atividadeId).orElseThrow();
		atividade.reabrirAtividade();
	}
	/**
	 * Exibe uma atividade em sua forma textual
	 * @param atividadeId id da atividade a ser exibida
	 * @return toString() da atividade
	 */
	public String exibirAtividade(String atividadeId) {
		Atividade atividade = this.ar.getAtividade(atividadeId).orElseThrow();
		return atividade.toString();
	}
	/**
	 * Altera a descrição de uma atividade
	 * @param atividadeId id da atividade que vai ter sua descrição alterada
	 * @param descricao nova descrição da atividade
	 */
	public void alterarDescricaoAtividade(String atividadeId, String descricao) {
		Atividade atividade = this.ar.getAtividade(atividadeId).orElseThrow();
		if (atividade.isAtividadeEncerrada() || atividade.isAtividadeDesativada()) {
			throw new IllegalStateException();
		}
		atividade.alterarDescricaoAtividade(descricao);
	}
	/**
	 * Altera o responsável de uma atividade
	 * @param atividadeId Id da atividade a ter o resposável alterado
	 * @param novoCpfResponsavel Cpf do novo responsável pela atividade
	 */
	public void alterarResponsavelAtividade(String atividadeId, String novoCpfResponsavel) {
		Atividade atividade = this.ar.getAtividade(atividadeId).orElseThrow();
		atividade.alterarResponsavelAtividade(novoCpfResponsavel);
	}

	/**
	 * Pega uma tarefa de uma atividade
	 * @param atividadeId
	 * @return tarefa da atividade
	 */
	public Tarefa getTarefa(String idTarefa) {
		String idAtividade = idTarefa.split("-")[0]+ "-" +  idTarefa.split("-")[1];
		Atividade atividade = this.ar.getAtividade(idAtividade).orElseThrow();
		return atividade.getTarefa(idTarefa);
	}
	/**
	 * Cadastra uma tarefa na atividade
	 * @param nome Nome da tarefa
	 * @param atividadeId Id da atividade a qual a tarefa vai ser associada
	 * @param habilidades recomendadas para a tarefa
	 */
	public String cadastrarTarefa(String atividadeId, String nome, String[] habilidades) {
		Atividade atividade = this.ar.getAtividade(atividadeId).orElseThrow();
	    return atividade.cadastrarTarefa(nome, habilidades);
	    
	    
	}
	/**
	 * Pega o total de tarefas da coleção de objetos do tipo tarefa
	 * @param tarefas uma coleção de tarefas
	 * @return o tamanho da coleção de tarefas
	 */
	public int getTotalTarefas(HashMap<String, Tarefa> tarefas) {
		return tarefas.size();
	}
	/**
	 * Remove uma tarefa a coleção de tarefas
	 * @param atividadeId id da atividade da qual
	 * a tarefa irá ser removida
	 */
	public void removerTarefa(String idTarefa) {
		String idAtividade = idTarefa.split("-")[0]+ "-" +  idTarefa.split("-")[1];
		Atividade atividade = this.ar.getAtividade(idAtividade).orElseThrow();
		atividade.removerTarefa(idTarefa);
	}
	/**
	 * pega o id da atividade 
	 * @return o id da atividade
	 */
	public String getIdAtividade() {
		Atividade atividade = this.ar.getAtividade(cpfResponsavel).orElseThrow();
		return atividade.getIdAtividade();
	}
	/**
	 * Pega uma atividade de acordo com o seu Id
	 * @param atividadeId id da atividade a ser pegada
	 * @return a atividade com o Id passado como parâmetro
	 */
	public Atividade getAtividade(String atividadeId) {
		return this.ar.getAtividade(atividadeId).orElseThrow();
	}
	
}
