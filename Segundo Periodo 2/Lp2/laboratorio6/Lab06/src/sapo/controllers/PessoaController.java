package sapo.controllers;

import sapo.services.PessoaService;

public class PessoaController {

	private PessoaService ps;

	public PessoaController(PessoaService ps) {
		this.ps = ps;
	}

	/*
	 * Método que cadastra uma pessoa atraves do seu cpf, nome e habilidades. E
	 * também faz as validações necessárias, como verificar se o cpf e nome sao
	 * vazios ou nulos e também se as habilidades são nulas.
	 */
	public void cadastraPessoa(String cpf, String nome, String[] habilidades) {
		if (cpf == null || cpf.isBlank()) {
			throw new IllegalArgumentException("Cpf está vazio");
		}

		if (nome == null || nome.isBlank()) {
			throw new IllegalArgumentException("Nome está vazio");
		}

		if (habilidades == null) {
			throw new IllegalArgumentException("Habilidades está nula");
		}
		this.ps.cadastraPessoa(cpf, nome, habilidades);
	}

	public void cadastraAluno(String cpf, String nome, String matr, int periodo, String[] habilidades) {
		this.ps.cadastraAluno(cpf, nome, matr, periodo, habilidades);
	}

	public void cadastraProfessor(String cpf, String nome, String siepe, String disciplinas, String[] habilidades) {
		this.ps.cadastraProfessor(cpf, nome, siepe, disciplinas, habilidades);
	}

	public void definirFuncaoProfessor(String cpf, String siape, String[] disciplinas) {
		this.ps.definirFuncaoProfessor(cpf, siape, disciplinas);
	}

	public void definirFuncaoAluno(String cpf, String matr, int periodo) {
		this.ps.definirFuncaoAluno(cpf, matr, periodo);
	}

	/*
	 * Método que exibe uma pessoa através de seu cpf e retornando uma representação
	 * textual da mesma.
	 */
	public String exibePessoa(String cpf) {
		return this.ps.exibePessoa(cpf);
	}

	/*
	 * Método que altera o nome de uma pessoa através do seu cpf e o novo nome para
	 * qual querem alterar, e também valida se o nome nome é nulo ou vazio.
	 */
	public void alteraNomePessoa(String cpf, String novoNome) {
		if (novoNome == null || novoNome.isBlank()) {
			throw new IllegalArgumentException("nome esta vazio");
		}

		this.ps.alteraNomePessoa(cpf, novoNome);

	}

	/*
	 * Método que altera as habilidades de uma pessoa através do seu cpf e as novas
	 * habilidades para qual querem alterar, e também valida se habilidades são
	 * nulas.
	 */
	public void alteraHabilidadesPessoa(String cpf, String[] novasHabilidades) {
		if (novasHabilidades == null) {
			throw new IllegalArgumentException("habilidades esta nula");
		}
		this.ps.alteraHabilidadesPessoa(cpf, novasHabilidades);

	}

	/*
	 * Método que remove uma poessoa através de seu cpf
	 */
	public void removePessoa(String cpf) {
		this.ps.removePessoa(cpf);

	}

	/*
	 * Método que adiciona um comentário em uma pessoa, através do cpf da pessoa
	 * para qual vai ser o comentário, o comentário em si e o cpf do autor do
	 * comentário
	 */
	public void adicionaComentarioPessoa(String cpf, String comentario, String cpfAutor) {
		this.ps.adicionaComentarioPessoa(cpf, comentario, cpfAutor);

	}

	/*
	 * Método que lista os comentários que uma pessoa possui através do seu cpf
	 */
	public String listaComentariosPessoa(String cpf) {
		return this.ps.listaComentariosPessoa(cpf);
	}
}
