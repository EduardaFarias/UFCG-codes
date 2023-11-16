package sapo.controllers;

import sapo.services.BuscaService;

public class BuscaController {

	private BuscaService bs;

	public BuscaController(BuscaService bs) {
		this.bs = bs;
	}

	/*
	 * Método que retorna um array de strings de pessoas buscadas e verifica se a
	 * consulta feita é nula ou vazia.
	 */
	public String[] exibirPessoas(String consulta) {
		if (consulta == null || consulta.isBlank()) {
			throw new IllegalArgumentException("Consulta está vazia");
		}
		return bs.exibirPessoas(consulta);
	}

	/*
	 * Método que retorna um array de strings de atividades buscadas e verifica se a
	 * consulta feita é nula ou vazia.
	 */
	public String[] buscarAtividade(String consulta) {
		if (consulta == null || consulta.isBlank()) {
			throw new IllegalArgumentException("Consulta está vazia");
		}
		return bs.buscarAtividade(consulta);
	}

	/*
	 * Método que retorna um array de strings de tarefas buscadas e verifica se o
	 * nome é nulo ou vazio.
	 */
	public String[] buscarTarefas(String nome) {
		if (nome == null || nome.isBlank()) {
			throw new IllegalArgumentException("Nome está vazio");
		}
		return bs.buscarTarefas(nome);
	}

	/*
	 * Método que busca tarefas através do id de uma atividade e o nome. Também
	 * verifica se o id da atividade e o nome é nulo ou vazio.
	 */
	public String[] buscarTarefas(String idAtividade, String nome) {
		if (idAtividade == null || idAtividade.isBlank()) {
			throw new IllegalArgumentException("Id da atividade está vazio");
		}

		if (nome == null || nome.isBlank()) {
			throw new IllegalArgumentException("Nome está vazio");
		}
		return bs.buscarTarefas(idAtividade, nome);
	}

	/*
	 * Método que sugere tarefas através do id de uma atividade e o nome. Também
	 * verifica se o id da atividade e o nome é nulo ou vazio.
	 */
	public String[] sugerirTarefas(String id, String cpf) {
		if (id == null || id.isBlank()) {
			throw new IllegalArgumentException("Id está vazio");
		}

		if (cpf == null || cpf.isBlank()) {
			throw new IllegalArgumentException("Cpf está vazio");
		}

		return bs.sugerirTarefas(id, cpf);
	}

	/*
	 * Método que retorna um array de Strings das buscas mais recentes e valida de o
	 * número de buscas é menor ou igual a zero.
	 */
	public String[] buscasMaisRecentes(int nBuscas) {
		if (nBuscas <= 0) {
			throw new IllegalArgumentException("Número de busca inválida");
		}

		return bs.buscaMaisRecentes(nBuscas);
	}

	/*
	 * Método que retorna um array de strings que exibe históricos de buscas feitas
	 * através de um index, e também verifica se o index a ser pesquisado é menor ou
	 * igual a zero.
	 */
	public String[] exibirHistoricoBusca(int indexBusca) {
		if (indexBusca < 0) {
			throw new IllegalArgumentException("O index da busca está vazio");
		}

		return bs.exibirHistoricoBusca(indexBusca);
	}

}
