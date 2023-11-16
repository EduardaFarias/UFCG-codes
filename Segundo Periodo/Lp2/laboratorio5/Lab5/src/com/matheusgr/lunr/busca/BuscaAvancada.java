package com.matheusgr.lunr.busca;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.matheusgr.lunr.documento.Documento;
import com.matheusgr.lunr.documento.DocumentoService;

/**
 * Uma das classes que irá implementar a interface Busca
 * representa uma busca, que pode ser simples ou avançada
 * @author eduarda
 *
 */

public class BuscaAvancada implements Busca {

	private Map<String, String> metadados;
	
	
	/**
	 * Construtor padrão, inicializa a criação de um Map de metadados
	 * 
	 * @param metadados Um conjunto de metadados
	 */
	public BuscaAvancada(Map<String, String> metadados) {
		this.metadados = metadados;

	}
	
	/**
	 * Operação de busca de documentos a partir de seus metadados
	 * @param ds DocumentoService a ser utilizado para busca.
	 */
	public Map<Documento, Integer> busca(DocumentoService ds) {
		Map<Documento, Integer> respostaDocumento = new HashMap<>();
		for (Documento d : ds.busca(metadados)) {
			respostaDocumento.put(d, respostaDocumento.getOrDefault(d, 0) + 1);
		}
		return respostaDocumento;
	}
/**
 *  Descreve uma consulta para explicar a consulta que foi feita.
	 * 
	 * @return Descrição da busca, que retorna a String
	 * metadado, a sua posição, a chave e o valor da chave.
 */

	
	public String[][] descreveConsulta() {
		String[][] resultado = new String[this.metadados.size()][];
		List<String> listaChaves = metadados.keySet().stream().collect(Collectors.toList());
		for (int i = 0; i < resultado.length; i++) {
			String chave = listaChaves.get(i);
			resultado[i] = new String[] { "METADADO " + (i + 1), chave, metadados.get(chave) };
		}

		return resultado;
	}

}

