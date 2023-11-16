package com.matheusgr.apresentacao;

import com.matheusgr.lunr.documento.Documento;

/**
 * Uma das classes que irá implementar a interface Apresentação
 * representa as duas primeiras linhas do texto do documento
 * @author eduarda
 *
 */

public class ApresentacaoPrimeirasLinhas implements Apresentacao {
	String tipoApresentacao;
	
	/**
	 * Construtor padrão, inicializa a apresentação das primeiras linhas
	 * 
	 * @param tipoApresentacao Tipo de apresentação a set utilizado
	 */
	public ApresentacaoPrimeirasLinhas(String tipoApresentacao) {
		this.tipoApresentacao = tipoApresentacao;
	}

	/**
	 * Operação de apresentação de um documento. Para a apresentação
	 * será possível exibição das duas primeiras linhas do documento.
	 * Caso o documento possua apenas duas linhas, só irá ser retornado
	 * a primeira linha do texto.
	 * @param docId documento a ser apresentado as duas primeiras linhas
	 */
	public String apresenta(Documento doc1) {
		String original = doc1.getOriginal();
		String[] linhas = original.split("\n");
		String primeirasLinhas ="";
		if(linhas.length == 2) {
			for(int i = 0; i < 1; i++) {
				primeirasLinhas += (linhas[i] + "\n");
			}
		}else
		for (int i = 0; i < 2; i++) {
			primeirasLinhas += (linhas[i] + "\n");
			}
		primeirasLinhas = primeirasLinhas.substring(0, primeirasLinhas.length() - 1);
			
		return primeirasLinhas; 
	}
}
