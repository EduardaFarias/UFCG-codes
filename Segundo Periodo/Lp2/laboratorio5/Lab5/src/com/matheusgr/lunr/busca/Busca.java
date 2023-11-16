package com.matheusgr.lunr.busca;

import java.util.Map;
/**
 * Interface de busca, onde as suas implementações 
 * retorna uma busca
 * @author eduarda
 */

import com.matheusgr.lunr.documento.Documento;
import com.matheusgr.lunr.documento.DocumentoService;

public interface Busca  {
	public Map<Documento, Integer> busca(DocumentoService ds);
	public String[][] descreveConsulta();
	
}
