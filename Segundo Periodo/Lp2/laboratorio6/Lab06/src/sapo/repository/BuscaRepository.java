package sapo.repository;

import java.util.ArrayList;
import java.util.List;

import sapo.entidades.Busca;

public class BuscaRepository {

	private List<Busca> buscas;

	public BuscaRepository() {
		this.buscas = new ArrayList<Busca>();
	}

	/*
	 * Método que adiciona uma busca
	 */
	public void addBusca(Busca busca) {
		this.buscas.add(busca);
	}

	/*
	 * Métopdo que retorna um array de strings de uma busca recente.
	 */
	public String[] buscaMaisRecentes(int nBuscas) {
		return buscas.subList(buscas.size() - nBuscas, buscas.size()).toArray(String[]::new);
	}

	/*
	 * Método que retorna um array de string de históricos buscados.
	 */
	public String[] exibirHistoricoBusca(int indexBusca) {
		return buscas.get(indexBusca).toArray();
	}

}
