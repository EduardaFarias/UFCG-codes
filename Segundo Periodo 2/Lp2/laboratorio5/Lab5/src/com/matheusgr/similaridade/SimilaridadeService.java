package com.matheusgr.similaridade;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.matheusgr.lunr.documento.Documento;
import com.matheusgr.lunr.documento.DocumentoService;

/**
 * Componente para tratamento da lógica de negócio relativa a similaridade.
 */
public class SimilaridadeService {

	private DocumentoService documentoService;
	

	/**
	 * Inicialização da lógica de serviço.
	 * 
	 * @param documentoService DocumentoService a ser utilizado pelo
	 *                         SimilaridadeService.
	 */
	public SimilaridadeService(DocumentoService documentoService) {
		this.documentoService = documentoService;
	}

	/**
	 * Calcula e retorna a similaridade.
	 * 
	 * Para o cálculo da similaridade:
	 * <ul>
	 * <li>Pega o documento 1</li>
	 * <li>Pega o documento 2</li>
	 * <li>Pega os termos do documento 1 e coloca em um conjunto (Termos1)</li>
	 * <li>Pega os termos do documento 2 e coloca em um conjunto (Termos2)</li>
	 * <li>Calcula a interseção entre Termos1 e Termos2 (Inters)</li>
	 * <li>Calcula a união entre Termos1 e Termos2 (Uniao)</li>
	 * <li>A similaridade é o tamanho de Inters sobre o tamanho do conjunto
	 * Uniao</li>
	 * </ul>
	 * 
	 * @param docId1 Documento 1.
	 * @param docId2 Documento 2.
	 * @return Valor de similaridade (entre 0 e 1, inclusives) representando a
	 *         semelhança entre os documentos.
	 */
	public double similaridade(String docId1, String docId2) {
		Optional<Documento> doc1 = this.documentoService.recuperaDocumento(docId1);
		Optional<Documento> doc2 = this.documentoService.recuperaDocumento(docId2);
		if(doc1.isEmpty()) {}
		// --> (TAMANHO DA INTERSEÇÃO) / (TAMANHO DA UNIÃO DOS CONJUNTOS)
		// Pega documento1, coloca os termos em um conjunto
		// Pega documento 2, coloca os termos em outro conjunto
		Set<String> set1 = new HashSet<>();
		Set<String> set2 = new HashSet<>();
		for (String string : doc1.get().getTexto()) {
			set1.add(string);
		}
		for (String string : doc2.get().getTexto()) {
			set2.add(string);
		}
		Set<String> uniao = new HashSet<String>();
			
		uniao.addAll(set1);
		uniao.addAll(set2);
		Set<String> intersecao = new HashSet<String>();
		intersecao.addAll(set1);
		intersecao.retainAll(set2);
		return (double) intersecao.size() / uniao.size();
		
		
	}
}

