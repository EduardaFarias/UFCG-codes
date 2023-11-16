package lab2;
import java.util.Arrays;
/**
 * Representação de uma disciplina, toda disciplina precisa ter um nome, a quantidade horas que foi usada nela,
 * a media do aluno e a lista de notas desse aluno.
 * 
 * @author Eduarda Farias.
 */
public class Disciplina {
	/**
	 * String referente ao nome da disciplina.
	 */
	private String nomeDisciplina; 
	/**
	 * Referente a quantidade de horas cadastradas.
	 */
	private int horas;
	/**
	 * Referente a média do aluno
	 */
	private double media;
	/** Referente ao Array listaNotas, que vai armazenar os 
	 *  quatros notas dos alunos.
	 */
	private double[] listaNotas = new double[4];
	
	/**
	 * Constrói uma disciplina a partir do nome da disciplina
	 * @param nomeDisciplina nome da disciplina.
	 */
	Disciplina(String nomeDisciplina){
		this.nomeDisciplina = nomeDisciplina; 
	}
	/**
	 * O método não possui retorno. Atribuí as horas cadastradas a horas.
	 * @return não possui retorno.
	 * @param horas quantidade de horas cadastradas
	 */
	public void cadastraHoras(int horas) {
		this.horas += horas;
	}
	/**
	 * O método não possui retorno. Atribui a listaNota valorNota.
	 * @return não possui retorno.
	 * @param nota a posição da nota na ListaNotas
	 * @param valorNota o valor da nota que foi cadastrada
	 */
	public void cadastraNota(int nota, double valorNota) {
		this.listaNotas[nota- 1] = valorNota;
	}
	/**
	 * O método possui retorno de um booleano que representa se o aluno foi aprovado ou não. A representação
	 * segue o formato "true" ou "false"
	 * @return a representação em booleano, se a média for maior ou igual a 7 retorna true,
	 * se não, retorne false.
	 */
	public boolean aprovado() {
		double media = 0;
		for (int i = 0; i < listaNotas.length; i++) {
			media += listaNotas[i];
		} 
		this.media = media / listaNotas.length;
		if (this.media >= 7.0) {
			return true;
		}
		return false;
	} 
	
	/**
	 * Retorna a String do nome da disciplina, mais as horas cadastradas, a média
	 * de um aluno e um array com as notas cadastradas de um aluno.
	 * A representação segue o formato de "nome da disciplina horas cadastradas".
	 * @return A representação em String do nome da disciplina, a quantidade de horas gastas nela,
	 * a média do aluno e o array com notas.
	 */
	public String toString() {
		return nomeDisciplina + " " + horas + " " + media + " " + (Arrays.toString(listaNotas));
	}
}
