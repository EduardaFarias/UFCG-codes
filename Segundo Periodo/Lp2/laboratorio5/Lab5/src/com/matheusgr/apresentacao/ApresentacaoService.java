package com.matheusgr.apresentacao;

import java.util.Optional;

import com.matheusgr.lunr.documento.Documento;
import com.matheusgr.lunr.documento.DocumentoService;

/**
 * Componente para tratamento da lógica de negócio relativa a apresentação de
 * documentos.
 */
public class ApresentacaoService {

	private DocumentoService documentoService;

	/**
	 * Inicialização da lógica de serviço.
	 * 
	 * @param documentoService DocumentoService a ser utilizado pelo ApresentacaoService.
	 */
	public ApresentacaoService(DocumentoService documentoService) {
		this.documentoService = documentoService;
	}

	/**
	 * Realiza a apresentação do documento indicado pelo tipo de apresentação.
	 * 
	 * @param docId1    Documento a ser apresentado.
	 * @param tipoApresentacao modo de apresentacao a ser aplicado sobre o documento.
	 */
	public String apresenta(String docId, String tipoApresentacao) {
		Optional<Documento> doc1 = this.documentoService.recuperaDocumento(docId);
		Apresentacao exibeApresentacao = null;
		if (doc1.isEmpty()) {
			throw new IllegalArgumentException("ID inválido");
		}
		switch (tipoApresentacao) {
		case "CAIXA ALTA":
			exibeApresentacao = new ApresentacaoCaixaAlta();
			break;

		case "PRIMEIRAS LINHAS":
			exibeApresentacao = new ApresentacaoPrimeirasLinhas(tipoApresentacao);
			break;

		case "ULTIMAS LINHAS":
			exibeApresentacao = new ApresentacaoUltimasLinhas();
			break;

		default:
			break;
		}

		return exibeApresentacao.apresenta(doc1.get());
	}

}
