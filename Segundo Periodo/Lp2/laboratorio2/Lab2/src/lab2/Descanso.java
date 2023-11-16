package lab2;
/**
 * Representação da rotina de descanso do aluno, por meio da relação 
 * entre a quantidade de horas descansadas e o número de semanas,
 * retornando o estado do aluno(cansado ou descansado).
 *
 *@author Eduarda Farias
*/
class Descanso {
	/**
	 * Quantidade de horas descansadas pelo aluno.
	*/
	private int horasDescansadas;
	/**
	 * Quantidade de semanas descansadas pelo aluno.
	 */
	private int numeroDeSemanas;

	/**
	 * O método não possui retorno. Atribuí quantidade de horas descansadas a valor.
	 * @return não possui retorno.
	 * @param valor representa a quantidade de horas descansadas.
	 */
	public void defineHorasDescanso(int valor) {
		this.horasDescansadas = valor;
	}
	/**
	 * O método não possui retorno. Atribuí quantidade de semanas a valor. 
	 * @return não possui retorno.
	 * @param valor representa o número de semanas descansadas.
	 */
	public void defineNumeroSemanas(int valor) {
		this.numeroDeSemanas = valor; 
	}
	/**
	 * Retorna a String que representa o estado do aluno.
	 * A representação segue no formato: "cansado" caso o aluno não tenha descansado no mínino 26 horas a cada semana,
	 * ou "descansado", caso ele tenha descansado pelo menos 26 horas a cada semana.
	 * @return a representação em String do estado do aluno. 
	 */
	public String getStatusGeral() {
		if(this.horasDescansadas == 0 || this.numeroDeSemanas == 0) {
			return "cansado";
		}
		else if((horasDescansadas) / (numeroDeSemanas) >= 26) {
			return "descansado";
		}	
		else {
			return "cansado";
		}
	}  
}
