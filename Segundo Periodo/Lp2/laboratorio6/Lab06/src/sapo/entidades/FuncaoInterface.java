package sapo.entidades;

/**
 * Classe que implementa uma funcao para uma pessoa
 * podendo ela ser aluno(a) ou professor(a)
 *
 */

public interface FuncaoInterface {
	
	
	public String funcao();
	
	public String[] listarPessoas(String cpf);
	
	/**
	 * 
	 * @return representacao textual, seja de aluno(a) ou
	 * professor(a)
	 */
	public String  ToString();
	
	/**
	 * metodo que adiciona uma funcao a uma pessoa
	 */
	
    public void removerFuncao(String cpf);
    /**
     * metodo que pegar o nivel de uma pessoa apartir
     * do seu cpf
     * @param cpf recebe cpf da pessoa
     */

	public void pegarNivel(String cpf);
	
}
