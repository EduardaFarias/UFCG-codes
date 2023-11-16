package com.matheusgr.apresentacao;

import com.matheusgr.lunr.documento.Documento;
/**
 * Interface de apresentação, onde as suas implementações 
 * geram um tipo de apresentação do texto.
 * @author eduarda
 *
 */

public interface Apresentacao {
	public String apresenta(Documento doc1);
}
