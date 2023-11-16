package sapo.repository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import sapo.entidades.Aluno;
import sapo.entidades.Pessoa;
import sapo.entidades.Professor;

public class PessoaRepository {

	private HashMap<String, Pessoa> pessoas;

	public PessoaRepository() {
		this.pessoas = new HashMap<>();
	}

	/*
	 * Método que cadastra uma pessoa.
	 */
	public void cadastraPessoa(Pessoa pessoa) {
		this.pessoas.put(pessoa.getCpf(), pessoa);
	}

	public void cadastraAluno(Pessoa aluno) {
		this.pessoas.put(aluno.getCpf(), aluno);

	}

	public void cadastraProfessor(Pessoa professor) {
		this.pessoas.put(professor.getCpf(), professor);
	}

	/*
	 * Método que remove uma pessoa através de seu cpf e verifica se a pessoa
	 * existe.
	 */
	public void removePessoa(String cpf) {
		Pessoa pessoa = pessoas.remove(cpf);
		if (pessoa == null) {
			throw new NoSuchElementException("Pessoa não existe");
		}
		for (Pessoa p : pessoas.values()) {
			p.removeComentariosAutor(pessoa);
		}
	}

	/*
	 * Método que adiciona um comentário em uma pessoa, através do cpf da pessoa
	 * para qual vai ser o comentário, o comentário em si e o cpf do autor do
	 * comentário
	 */
	public void adicionaComentarioPessoa(String cpf, String comentario, String autorCpf) {
		Pessoa pessoa = this.pessoas.get(cpf);
		Pessoa autor = this.pessoas.get(autorCpf);
		pessoa.adicionaComentario(comentario, autor);
	}

	/*
	 * Método que verifica se existe uma pessoa através do cpf
	 */
	public boolean existePessoa(String cpf) {
		return this.pessoas.containsKey(cpf);
	}

	public Pessoa getPessoa(String cpf) {
		return this.pessoas.get(cpf);
	}

	/*
	 * Método que vai retornar um array de pessoas, ordenadas pelo nome, a partir de
	 * uma consulta realizada.
	 */

	public List<Pessoa> buscaPessoas(String[] consulta) {
		return pessoas.values().stream().filter(p -> pessoaContainsConsulta(consulta, p))
				.sorted(Comparator.comparing(Pessoa::getNome)).collect(Collectors.toList());
	}

	/*
	 * Método que verifica ses tem uma determinada consulta em uma pessoaa.
	 */
	private boolean pessoaContainsConsulta(String[] consulta, Pessoa pessoa) {
		for (String palavra : consulta) {
			if (!pessoa.toString().toLowerCase().contains(palavra)) {
				return false;
			}
		}
		return true;
	}

}
