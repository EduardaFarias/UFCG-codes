package sapo.services;

import sapo.entidades.Aluno;
import sapo.entidades.Pessoa;
import sapo.entidades.Professor;
import sapo.entidades.VerificacaoGeral;
import sapo.repository.PessoaRepository;

public class PessoaService {

	private PessoaRepository pr;
	private VerificacaoGeral vg;

	public PessoaService() {
		this.pr = new PessoaRepository();
		this.vg = new VerificacaoGeral();
	}

	/*
	 * Método que cadastra uma pessoa e verifica se uma pessoa já está cadastrada.
	 */
	public void cadastraPessoa(String cpf, String nome, String[] habilidades) {
		if (pr.existePessoa(cpf)) {
			throw new IllegalArgumentException("Pessoa já cadastrada");
		}
		Pessoa pessoa = new Pessoa(cpf, nome, habilidades);
		this.pr.cadastraPessoa(pessoa);

	}

	public void cadastraAluno(String cpf, String nome, String matr, int periodo, String[] habilidades) {
		this.vg.verificaPessoaExiste(nome);
		Pessoa pessoa = new Pessoa(cpf, nome, matr, periodo, habilidades);
		this.pr.cadastraAluno(pessoa);

	}

	public void cadastraProfessor(String cpf, String nome, String siepe, String disciplinas, String[] habilidades) {
		this.vg.verificaPessoaExiste(nome);
		Pessoa pessoa = new Pessoa(cpf, nome, siepe, disciplinas, habilidades);
		this.pr.cadastraProfessor(pessoa);

	}

	/*
	 * Método que retorna a representação textual de uma pessoa através de seu cpf.
	 */
	public String exibePessoa(String cpf) {
		Pessoa pessoa = this.pr.getPessoa(cpf);
		this.vg.verificaPessoaExiste(cpf);
		return pessoa.toString();

	}

	/*
	 * Método que altera o nome de uma pessoa através de um cpf e o novo nome.
	 */
	public void alteraNomePessoa(String cpf, String novoNome) {
		Pessoa pessoa = this.pr.getPessoa(cpf);
		pessoa.alteraNomePessoa(novoNome);
	}

	/*
	 * Métodos que altera as habilidades de uma pessoa através de seu cpf.
	 */
	public void alteraHabilidadesPessoa(String cpf, String[] novasHabilidades) {
		Pessoa pessoa = this.pr.getPessoa(cpf);
		pessoa.alteraHabilidadesPessoa(novasHabilidades);

	}

	/*
	 * Método que remove uma pessoa através de um cpf.
	 */
	public void removePessoa(String cpf) {
		this.pr.removePessoa(cpf);
	}

	/*
	 * Método que adiciona um comentário em uma pessoa.
	 */
	public void adicionaComentarioPessoa(String cpf, String comentario, String cpfAutor) {
		this.pr.adicionaComentarioPessoa(cpf, comentario, cpfAutor);
	}

	/*
	 * Método que lista os comentários de uma pessoa atraves do cpf.
	 */
	public String listaComentariosPessoa(String cpf) {
		Pessoa pessoa = this.pr.getPessoa(cpf);
		return pessoa.listaComentarios();
	}

	public Pessoa getPessoa(String cpf) {
		return this.pr.getPessoa(cpf);
	}

	public void definirFuncaoProfessor(String cpf, String siape, String[] disciplinas) {
      
	}

	public void definirFuncaoAluno(String cpf, String matr, int periodo) {
		// TODO Auto-generated method stub

	}

}
