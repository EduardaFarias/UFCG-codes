package sapo.entidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pessoa {

	private String cpf;
	private String nome;
	private String[] habilidades;
	private List<Comentario> comentarios = new ArrayList<>();
	private Funcao funcao;


	public Pessoa(String cpf, String nome, String[] habilidades) {
		this.cpf = cpf;
		this.nome = nome;
		this.habilidades = habilidades;
		this.funcao = null;
	}

	public Pessoa(String cpf, String nome, String matr, int periodo, String[] habilidades) {
		this.cpf = cpf;
		this.nome = nome;
		this.habilidades = habilidades;
	    this.funcao = new Aluno(cpf, matr, periodo);
	}

	public Pessoa(String cpf, String nome, String siepe, String disciplinas, String[] habilidades) {
		this.cpf = cpf;
		this.nome = nome;
		this.habilidades = habilidades;
		this.funcao = new Professor(cpf, siepe, disciplinas);
	}
	
	public void definirFuncaoProfessor(String cpf, String siepe, String disciplinas) {
		this.funcao = new Professor(cpf, siepe,disciplinas);
		
	}
	
    public void definirFuncaoAluno(String cpf, String matr, int periodo) {
		this.funcao = new Aluno(cpf, matr, periodo);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setHabilidades(String[] habilidades) {
		this.habilidades = habilidades;
	}


	/*
	 * Método que adiciona um comentário em uma pessoa, através de um comentário e
	 * um autor.
	 */
	public void adicionaComentario(String comentario, Pessoa autor) {
		Comentario coment = new Comentario(autor, comentario);
		comentarios.add(coment);
	}

	/*
	 * Método que retorna a representação textual de comentários.
	 */
	public String listaComentarios() {
		String saida = nome + "–" + cpf + "\n" + "Comentários:\n";
		for (int i = 0; i < comentarios.size(); i++) {
			saida += comentarios.get(i).toString();
		}

		return saida.trim();
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {

		return cpf;
	}

	public String[] getHabilidades() {
		return this.habilidades;
	}

	/*
	 * Método que remove um comentario através de um autor.
	 */
	public void removeComentariosAutor(Pessoa autor) {
		for (Comentario coment : comentarios) {
			if (coment.getAutor().equals(autor)) {
				comentarios.remove(coment);
			}
		}

	}

	public void alteraNomePessoa(String novoNome) {
		this.nome = novoNome;
	}

	public void alteraHabilidadesPessoa(String[] novasHabilidades) {
		this.habilidades = novasHabilidades;

	}
	
	public String toString() {
		Arrays.sort(habilidades);
		String habi = "\n";
		for (String h : habilidades) {
			habi += "- " + h + "\n";
		}
		String mensagem =  this.nome + " - " + this.cpf + habi;
		if (this.funcao != null) {
			mensagem += "\n"+ this.funcao.funcao();
		}
		
		mensagem += "\nComentários:";
		for (int i = 0; i < comentarios.size(); i++) {
			mensagem += "\n"+ comentarios.get(i).toString();
		}
		

		return mensagem ;
		
	}
}


	
