package sapo.entidades;

import java.util.Arrays;

/**
 * classe que determina a funcao professor para uma pessoa, que possue
 * disciplinas e siepe.
 * 
 *
 */

public class Professor extends Funcao {
	/**
	 * siepe de um professor(a)
	 */
	private String siepe;
	/**
	 * disciplinas de um professor(a)
	 */
	private String disciplinas;
	
	private String cpf;

	private Funcao funcao;

	/**
	 * 
	 * @param disciplinas que um professor(a) ministra
	 * @param habilidades que um professor(a) exerce
	 */

	public Professor(String cpf, String siepe, String disciplinas) {
		this.siepe = siepe;
		this.disciplinas = disciplinas;
		this.cpf = cpf;

	}

	/**
	 * 
	 * @return o siepe de um professor
	 */
	public String getSiepe() {
		return this.siepe;
	}

	/**
	 * 
	 * @return disciplinas que um professor ministra.
	 */

	public String getDisciplinas() {
		return this.disciplinas;
	}
	
	
	public String getCpf() {
		return this.cpf;
	}

	/**
	 * metodo que remove funcao de uma pessoa
	 */

	public void removeFuncao() {
		this.funcao = null;
	}

	/**
	 * representacao textual de professor
	 */
	
	@Override
	public String funcao() {
		String mensagem = "Professor" + " - " + this.getSiepe() + " - " + this.getDisciplinas();
		return  mensagem ;
	}

	

}
