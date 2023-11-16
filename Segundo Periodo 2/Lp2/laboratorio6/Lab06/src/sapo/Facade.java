package sapo;

import sapo.controllers.AtividadeController;
import sapo.controllers.PessoaController;
import sapo.controllers.TarefaController;
import sapo.services.AtividadeService;
import sapo.services.PessoaService;
import sapo.services.TarefaService;

public class Facade {

	private PessoaController pessoaController;
	private TarefaController tarefaController;
	private AtividadeController atividadeController;

	public Facade() {
		PessoaService ps = new PessoaService();
		AtividadeService as = new AtividadeService(ps);
		TarefaService ts = new TarefaService(ps, as);
		this.pessoaController = new PessoaController(ps);
		this.tarefaController = new TarefaController(ts);
		this.atividadeController = new AtividadeController(as);
	}

	/*
	 * Método que cadastra uma pessoa atraves do seu cpf, nome e habilidades
	 */
	public void cadastraPessoa(String cpf, String nome, String[] habilidades) {
		this.pessoaController.cadastraPessoa(cpf, nome, habilidades);
	}


	/*
	 * Método que exibe uma pessoa através de seu cpf
	 */
	public String exibePessoa(String cpf) {
		return this.pessoaController.exibePessoa(cpf);
	}

	/*
	 * Método que altera o nome de uma pessoa através do seu cpf e o novo nome para
	 * qual querem alterar
	 */
	public void alteraNomePessoa(String cpf, String novoNome) {
		this.pessoaController.alteraNomePessoa(cpf, novoNome);
	}

	/*
	 * Método que altera as habilidades de uma pessoa através do seu cpf e as novas
	 * habilidades para qual querem alterar
	 */
	public void alteraHabilidadesPessoa(String cpf, String[] novasHabilidades) {
		this.pessoaController.alteraHabilidadesPessoa(cpf, novasHabilidades);

	}

	/*
	 * Método que remove uma poessoa através de seu cpf
	 */
	public void removePessoa(String cpf) {
		this.pessoaController.removePessoa(cpf);
	}

	/*
	 * Método que adiciona um comentário em uma pessoa, através do cpf da pessoa
	 * para qual vai ser o comentário, o comentário em si e o cpf do autor do
	 * comentário
	 */
	public void adicionaComentarioPessoa(String cpf, String comentario, String cpfAutor) {
		this.pessoaController.adicionaComentarioPessoa(cpf, comentario, cpfAutor);
	}

	/*
	 * Método que lista os comentários que uma pessoa possui através do seu cpf
	 */
	public String listaComentariosPessoa(String cpf) {
		return this.pessoaController.listaComentariosPessoa(cpf);
	}
	
	
	public void cadastraAluno(String cpf, String nome, String matr, int periodo, String[] habilidades) {
	     this.pessoaController.cadastraAluno(cpf, nome, matr, periodo, habilidades);
	}
	
	public void cadastraProfessor(String cpf, String nome, String siepe, String disciplinas , String[] habilidades) {
	     this.pessoaController.cadastraProfessor(cpf, nome, siepe, disciplinas, habilidades);
	}
	
	/**
	 * Método que cadastra uma atividade através do nome da atividade, descrição 
	 * da atividade e cpf o responsável pela atividade.
	 * @param nome Nome da atividade a ser cadastrada
	 * @param descricao Descrição da atividade a ser cadastrada
	 * @param cpf Cpf do responsável pela atividade
	 */
	public String cadastrarAtividade(String nome, String descricao, String cpf) {
		return this.atividadeController.cadastrarAtividade(nome, descricao, cpf);
	}
	
	/**
	 * Método que encerra uma atividade caso não aja tarefas pendentes 
	 * para aquela atividade.
	 * @param atividadeId Id da atvidade a ser encerrada
	 */
	public void encerrarAtividade(String atividadeId) {
		this.atividadeController.encerrarAtividade(atividadeId);
	}
	/**
	 * Método que destaiva uma atividade caso ela não tenha mais 
	 * tarefas pendentes.
	 * @param atividadeId id da atividade a ser desativada
	 */
	public void desativarAtividade(String atividadeId) {
		this.atividadeController.desativarAtividade(atividadeId);
	}
	
	/**
	 * Reabre uma atividade
	 * @param atividadeId id da atividade a ser reaberta
	 */
	public void reabrirAtividade(String atividadeId) {
		this.atividadeController.reabrirAtividade(atividadeId);
	}
	
	/**
	 * Método que exibe uma atividade
	 * @param atividadeId id da atividade a ser exibida
	 * @return atividade em seu formato textual
	 */
	public String exibirAtividade(String atividadeId) {
		return this.atividadeController.exibirAtividade(atividadeId);
	}
	
	/**
	 * Método que altera a descriçao da atividade
	 * @param atividadeId Id da atividade que vai ter a descrição
	 * alterada
	 * @param descricao Nova descriçao da atividade
	 */
	public void alterarDescricaoAtividade(String atividadeId, String descricao) {
		this.atividadeController.alterarDescricaoAtividade(atividadeId, descricao);
	}
	
	/**
	 * Método que altera o responsavel  de uma atividade
	 * @param atividadeId id da atividade a ser mudado o responsavel
	 * @param cpf Cpf do novo responsavel pela atividade
	 */
	public void alterarResponsavelAtividade(String atividadeId, String cpf) {
		this.atividadeController.alterarResponsavelAtividade(atividadeId, cpf);
	}

	public String cadastrarTarefa(String atividdeId, String nome, String[] habilidades) {
		return this.tarefaController.cadastrarTarefa(atividdeId, nome, habilidades);
	}

	public void alterarNomeTarefa(String idTarefa,  String novoNome) {
		this.tarefaController.alterarNomeTarefa(idTarefa, novoNome);
	}

	public void alterarHabilidadesTarefa(String idTarefa, String[] habilidades) {
		this.tarefaController.alterarHabilidadesTarefa(idTarefa,  habilidades);
	}

	public void adicionarHorasTarefa(String idTarefa, int horas) {
		this.tarefaController.adicionaHorasTarefa(idTarefa, horas);
	}

	public void removeHorasTarefa(String idTarefa, int horas) {
		this.tarefaController.removerHorasTarefa(idTarefa, horas);
	}

	public void concluirTarefa(String idTarefa) {
		this.tarefaController.concluirTarefa(idTarefa);
	}

	public void removeTarefa(String cpf) {
		this.tarefaController.removeTarefa(cpf);
	}

	public String exibirTarefa(String idTarefa) {
		return this.tarefaController.exibirTarefa(idTarefa);
	}

	public void associarPessoaTarefa(String cpf, String idTarefa) {
		this.tarefaController.associarPessoaTarefa(cpf, idTarefa);
	}

	public void removerPessoaTarefa(String cpf, String idTarefa) {
		this.tarefaController.removerPessoaTarefa(cpf, idTarefa);
	}

}
