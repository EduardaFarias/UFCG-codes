package com.matheusgr.apresentacao;

import com.matheusgr.lunr.documento.Documento;
/**
 * Uma das classes que irá implementar a interface Apresentação
 * representa as duas primeiras últimas do texto do documento
 * @author eduarda
 */

public class ApresentacaoUltimasLinhas implements Apresentacao{
	String tipoApresentacao;
	/**
	 * Operação de apresentação de um documento. Para a apresentação
	 * será possível exibição das duas últimas linhas do documento.
	 * Caso o documento possua apenas duas linhas, só irá ser retornado
	 * a segunda linha do texto.
	 * @param docId documento a ser apresentado as duas últimas linhas.
	 */
	public String apresenta(Documento doc1) {
		String original = doc1.getOriginal();
		String linhas[] = original.split("\n");
		String ultimasLinhas = "";
		if(linhas.length == 2) {
			for(int i = linhas.length - 1; i > linhas.length - 2; i--) {
				ultimasLinhas += linhas[i] + "\n";
			}
		}else {
		for(int i = linhas.length - 1; i > linhas.length - 3; i--) {
			ultimasLinhas += linhas[i] + "\n"; 
		}
		}
		
		ultimasLinhas = ultimasLinhas.substring(0, ultimasLinhas.length() - 1);
		return ultimasLinhas;
	}
	
}
