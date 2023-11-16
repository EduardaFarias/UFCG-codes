package com.matheusgr.lunr.documento;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Repositório de documentos. O repositório pode ter operações simples de busca,
 * mas a lógica de ranking, limitação e ordenação deve ficar em outra entidade.
 * 
 * O ID de um documento é único.
 */
class DocumentoRepository {

	Map<String, Documento> documentos = new HashMap<>();
	private ValidadorDocumentos validador;

	/**
	 * Construção padrão do repositório de documentos.
	 */
	DocumentoRepository() {
		this.documentos = new HashMap<>();
		this.validador = new ValidadorDocumentos();
	}

	/**
	 * Adiciona o documento. O documento é validado para garantir a consistência do
	 * documento (sem termos e id vazios).
	 * 
	 * @param d Documento a ser adicionado.
	 */
	void adiciona(Documento d) {
		this.validador.validacao(d.getId(), d.getTexto());
		this.documentos.put(d.getId(), d);
	}

	/**
	 * Recupera um documento do repositório.
	 * 
	 * @param id ID do documento.
	 * @return Documento, caso exista.
	 */
	Optional<Documento> recupera(String id) {
		Documento doc = null;
		this.validador.validacao(id);

		if (documentos.containsKey(id)) {
			doc = documentos.get(id);
		}
		return Optional.ofNullable(doc);
	}

	/**
	 * Retorna o total de documentos cadastrados.
	 * 
	 * @return O total de documentos cadastrados.
	 */
	int totalDocumentos() {
		return this.documentos.size();
	}

	/**
	 * Realiza uma busca pelos termos.
	 * 
	 * @param termo Termo a ser buscado.
	 * @return Conjunto de documentos com o termo.
	 */
	public Set<Documento> busca(String termo) {
		return (this.documentos.values()).stream().filter((x) -> Arrays.binarySearch(x.getTexto(), termo) > 0)
				.collect(Collectors.toSet());
	}

	/**
	 * Recebe um mapa de metados e vai verificar se todos metadados passados
	 * estão contidos em todos os documentos.
	 * 
	 * @param Mapa de metadados 
	 * @return Conjunto de documentos checados.
	 */
	public Set<Documento> busca(Map<String, String> metadados) {
		Set<Documento> documentosChecados = new HashSet<>();

		for (Documento documento : this.documentos.values()) {
			if (documento.getMetadados().keySet().containsAll(metadados.keySet())
					&& documento.getMetadados().values().containsAll(metadados.values())) {
				documentosChecados.add(documento);
			}
		}
		return documentosChecados;
	}

}




