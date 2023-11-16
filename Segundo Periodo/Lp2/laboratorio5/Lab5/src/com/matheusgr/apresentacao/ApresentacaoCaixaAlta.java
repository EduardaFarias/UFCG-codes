package com.matheusgr.apresentacao;

import com.matheusgr.lunr.documento.Documento;

/**
 * Uma das classes que irá implementar a interface Apresentação
 * representa o texto de doc1 em caixa alta.
 * @author eduarda
 *
 */
public class ApresentacaoCaixaAlta implements Apresentacao{
		/**
		 * Operação de apresentação de um documento. Para a apresentação
		 * será possível exibição do texto do documento em caixa alta.
		 * @param docId documento do qual irá ser retirado o texto que vai ser 
		 * passado para caixa alta 
		 */
		public String apresenta(Documento doc1) {
			return doc1.getOriginal().toUpperCase(); 
		}
}
