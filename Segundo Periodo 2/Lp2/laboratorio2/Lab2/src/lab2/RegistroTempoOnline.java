package lab2;

/**
 * Representação do registro do tempo online que o aluno dedica a uma disciplina remota, sendo possível o
 * acompanhamento da disciplina por meio da relação entre o tempo online esperado para ser dedicado a disciplina,
 * e o tempo utlizado para dedicar à disciplina.
 *
 * @author Eduarda Farias
 */
public class RegistroTempoOnline {
	/**
	 * String referente ao nome da disciplina
	 */
	private String nomeDisciplina;
	/**
	 * Refere-se ao tempo online que se espera que seja dedicado à disciplina.
	 */
	private int tempoOnlineEsperado;
	/**
	 * tempo realmente gasto na disciplina.
	 */
	private int tempo;
	
	/**
	 * Constrói um registro de tempo online da disciplina a partir do nome da disciplina.
	 * Por esperar que o aluno dedique o dobro de tempo online, e por considerar que disciplinas de 60 horas,
	 * toda disciplina começa com 120 horas.
	 * @param nomeDisciplina o nome da disciplina.
	 */
	RegistroTempoOnline(String nomeDisciplina){
		this(nomeDisciplina, 120);
	}
	/**
	 * Constrói um registro de tempo online a partir do nome da disciplina e do do tempo online esperado que se dediquue
	 * a disciplina.
	 * @param nomeDisciplina o nome da disciplina.
	 * @param tempoOnlineEsperado o tempo online esperado para a disciplina.
	 */
	RegistroTempoOnline (String nomeDisciplina, int tempoOnlineEsperado) {
		this.nomeDisciplina = nomeDisciplina;
		this.tempoOnlineEsperado = tempoOnlineEsperado;
	}
	/**
	 * O método não possui retorno, atribui tempo, caso o aluno adicione tempo online.
	 * @return não possui retorno.
	 * @param tempo o tempo online adicionado 
	 */
	public void adicionaTempoOnline(int tempo) {
		this.tempo += tempo;
	}
	/** Retorna um booleano que representa se o aluno atingiu o tempo online esperado ou não.
	 * A representação segue o fomato "true" ou "false"
	 * @return a representação em booleano, se um aluno atingiu o tempo esperado o método retona true, se não, retorna false.
	 */
	public boolean atingiuMetaTempoOnline() {
		if(tempo >= tempoOnlineEsperado) {
			return true; 
		}
		return false; 
	}
	/**
	 * Retorna a String que representa uma disciplina e o tempo gasto nele, e o tempo esperado que fosse gastado nela
	 * a reresentação segue o formato de "Nome da disciplina tempo gasto / tempoesperado".
	 * @return a representação em String de uma disciplina, o tempo gasto nela, e o tempo esperado que seria gasto.
	 */
	public String toString() {
		return nomeDisciplina + " " + tempo+ "/" +tempoOnlineEsperado;  
	}
	
}
