package com.matheusgr.lunr;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ApresentacaoTest extends BaseTest{

	@Test
	void testCaixaAlta01() {
		this.documentoController.adicionaDocumentoTxt("A", "aaa");
		assertEquals("AAA", this.apresentacaoController.apresenta("A", "CAIXA ALTA"));
	}
	@Test
	void testPrimeirasLinhas() {
		this.documentoController.adicionaDocumentoTxt("AVD", "Uma ovelha comendo pera\nUm saguin pulando tranpolim\nBruxas andando nas escadas");
		assertEquals("Uma ovelha comendo pera\nUm saguin pulando tranpolim", this.apresentacaoController.apresenta("AVD", "PRIMEIRAS LINHAS"));
	}  
	
	@Test
	void testPrimeirasLinhasDuasLinhas() {
		this.documentoController.adicionaDocumentoTxt("TST", "Um fim de semana comendo lasanha de frango\nUm mês de férias na praia");
		assertEquals("Um fim de semana comendo lasanha de frango", this.apresentacaoController.apresenta("TST", "PRIMEIRAS LINHAS"));
	}
	
	@Test
	void testUltimasLinhasDuasLinhas() {
		this.documentoController.adicionaDocumentoTxt("DUD", "Eu sou linda\nUma abelha albina\nUma zebra monocromatica\nCancelei FMCC2");
		assertEquals("Cancelei FMCC2\nUma zebra monocromatica", this.apresentacaoController.apresenta("DUD", "ULTIMAS LINHAS"));
	}
	
	@Test
	void testUltimasLinhasDuasLinhas02() {
		this.documentoController.adicionaDocumentoTxt("GDC", "Uma cachorrinha salscinha se chama Canella\nA dona dela se chama Deyse");
		assertEquals("A dona dela se chama Deyse", this.apresentacaoController.apresenta("GDC", "ULTIMAS LINHAS"));
	}
	

}
