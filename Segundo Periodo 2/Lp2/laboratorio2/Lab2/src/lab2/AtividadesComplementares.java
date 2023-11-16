package lab2;
/**
 * Representação das atividades complementares que auxiliam no registro dos 22
 * créditos necessários para o aluno conluir o curso.
 * @author EduardaFarias
 *
 */
public class AtividadesComplementares {
	/**
	 * Referente às horas de estágios cadastradas
	 */
	private int horasEstagio;
	/**
	 * Referente a quantidade de meses cadastrados.
	 */
	private int mesesProjeto;
	/**
	 * Referente a quantidade de horas cadastradas
	 */
	private double horasCurso;
	/**
	 * Referente ao total de créditos do aluno, inicialmente zerado.
	 */
	private int totalCreditos = 0;
	/**
	 * Refere-se a quantidade de créditos adquiridos por meio de projetos, inicialmente com 0.
	 */
	private int creditosProjeto = 0;
	/**
	 * Refere-se a quantidade de créditos adquiridos por meio de estágios, inicialmente com 0.
	 */
	private int creditosEstagios = 0;
	/**
	 * Refere-se a quantidade de créditos adquiridos por meio de horas de cursos
	 */
	private int creditosCursos = 0;
	/**
	 * Uma lista que irá retornar os estágios.
	 */
	private int[] estagios = new int[9];
	/**
	 * Uma lista que irá retornar os projetos.
	 */
	private int[] projetos = new int[16];
	/*
	 * Refere-se a quantidade de estágios
	 */
	private int quantEstagios = 0;
	/**
	 * Refere-se a quantidade de projetos.
	 */
	private int quantProjetos = 0;

	AtividadesComplementares() {

	}
	/**
	 * O método não possui retorno. Atribui horas a horasEstagio
	 * @param horas horas de estágio
	 * @return não retorna nada
	 */
	public void adicionarEstagio(int horas) {
		this.horasEstagio += horas;
		this.estagios[this.quantEstagios] = horas;
		this.quantEstagios++;
	}

	/**
	 * O método não possui retorno. Atribui meses a mesesProjeto.
	 * @param meses
	 * @return não retorna nada.
	 */
	public void adicionarProjeto(int meses) {
		this.mesesProjeto += meses;
		this.projetos[this.quantProjetos] = meses;
		this.quantProjetos++;
	}

	/**
	 * O método não possui retorno. Atribui horas a horasCurso.
	 * @param horas
	 * @return não retorna nada 
	 */
	public void adicionarCurso(double horas) {
		this.horasCurso += horas;
	}

	/**
	 * O método não possui retorno. Conta a quantidade de creditos ganhos a cada 300 horas de estágio
	 * A cada 300 horas contadas são adicionados 5 créditos, usa-se a divisão que seleciona a parte inteira do resultado
	 * para atribuir às horas de estágio, multiplicando esse resultado pela quantidade de créditos(nesse caso, 5)
	 * @return não retorna nada.
	 */
	private void contaCreditosEstagios() {
		this.creditosEstagios =(int)(this.horasEstagio / 300) * 5;
	}
	/**
	 * O método não possui retorno. Conta a quantidade de créditos ganhos a cada três meses de projetos.
	 * A cada 3 meses contados, são adicionados 2 créditos, usa-se a divisão que seleciona apenas a parte
	 * inteira do resultado para atribuir à quantidade de meses de projeto, multiplicando esse resultado pela quantidade
	 * de créditos, nesse caso, 2. 
	 */
	private void contaCreditosProjetos() {
		this.creditosProjeto  =  (int)(this.mesesProjeto /3) * 2;
	}
	/**
	 * O método não possui retorno. Conta a quantidade de créditos ganhos a cada 30 horas de curso.
	 * A cada 30 horas, são adicionados 1 crédito, usa-se a divisão que seleciona apenas a parte inteira
	 * do resultado para atribuir à quantidade de horas de curso, multiplicando  esse resultado pela quantidade
	 * de créditos, nesse caso 1, nem precisa multiplicar.
	 */
	private void contaCreditosCursos() {
		this.creditosCursos = (int)(this.horasCurso / 30);
	}
	/**
	 * Retorna o int que representa o total de créditos do aluno que um aluno possui após 
	 * a contagem de creditos dos eságios, projetos e cursos.
	 * @return a representação do int total de créditos que um aluno possui
	 */
	public int contaCreditos() {
		this.contaCreditosEstagios();
		this.contaCreditosProjetos();
		this.contaCreditosCursos();
		this.totalCreditos = (int)(this.creditosEstagios+ this.creditosProjeto + this.creditosCursos);
		return this.totalCreditos; 
		
	}
/**
 * Retorna um Array de Strings, que representa as atividades cadastradas(o tipo de atividade(que pode ser, estágio, cursos ou projetos))
 * e a quantidade de horas ou meses, dependendo do que foi cadastrado, e o total de créditos.
 * @return um Array de Strings
 */
	public String[] pegaAtividades() {
		this.contaCreditosEstagios();
		this.contaCreditosProjetos();
		this.contaCreditosCursos();
		int x = 0;
		String[] atividades = new String[4 + this.quantEstagios + this.quantProjetos];
		for (int i = 0; i < quantEstagios; i++) {
			atividades[x] = "Estagio " + this.estagios[i];
			x++;
		}
		for (int j = 0; j < quantProjetos; j++) {
			atividades[x] = "Projeto " + this.projetos[j];
			x++;
		}
		atividades[atividades.length - 4] = "Cursos " + this.horasCurso ;
		atividades[atividades.length - 3] = "Creditos_Estagio " + this.creditosEstagios ;
		atividades[atividades.length - 2] = "Creditos_Projeto " + this.creditosProjeto ;
		atividades[atividades.length - 1] = "Creditos_Cursos " + this.creditosCursos ;
		return atividades; 
	}
}
