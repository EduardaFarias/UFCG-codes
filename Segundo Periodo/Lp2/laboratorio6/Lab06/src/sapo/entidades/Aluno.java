package sapo.entidades;



/**
 * Classe aluno que herda atributos de pessoa. Aluno possui periodo e matricula.
 * 
 */

public class Aluno extends Funcao{

	/**
	 * matricula do aluno
	 */

	private String matr;

	/**
	 * periodo no qual o aluno esta.
	 */
	private int periodo;

	private String cpf;



	/**
	 * Construtor de aluno
	 * 
	 * @param cpf         cpf do aluno.
	 * @param nome        nome do aluno.
	 * @param matr        matricula do aluno.
	 * @param periodo     periodo do aluno.
	 * @param habilidades habilidades que o aluno possue.
	 */
	public Aluno(String cpf, String matr, int periodo) {
		this.matr = matr;
		this.periodo = periodo;
        this.cpf = cpf;
	}

	/**
	 * 
	 * @return matricula do aluno
	 */
	public String getMatricula() {
		return this.matr;
	}

	/**
	 * 
	 * @return periodo do aluno
	 */

	public int getPeriodo() {
		return this.periodo;
	}
	
	
	public String getCpf() {
		return this.cpf;
	}

	/**
	 * @return representacao textual de aluno
	 */
	
	
	@Override
	public String funcao() {
		return "Aluno" + " - " + this.getMatricula() + " - " + this.getPeriodo();
		
	}

	
}


