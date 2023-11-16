package com.matheusgr.lunr;

import org.junit.jupiter.api.BeforeEach;

import com.matheusgr.apresentacao.ApresentacaoController;
import com.matheusgr.lunr.busca.BuscaController;
import com.matheusgr.lunr.documento.DocumentoController;
import com.matheusgr.similaridade.SimilaridadeController;

class BaseTest {

	public static final String TEXTO1_ID = "789";
	public static final String TEXTO2_ID = "ABC";
	public static final String JAVA_ID = "456";
	public static final String HTML_ID = "123";
	public static final String CASA01_ID = "CAA";
	public static final String CASA02_ID = "CEEI";
	public static final String EXEMPLO01_ID = "LSDD";
	
	
	protected DocumentoController documentoController;
	protected BuscaController buscaController;
	protected SimilaridadeController similaridadeController;
	protected ApresentacaoController apresentacaoController;

	@BeforeEach
	void setUp() throws Exception {
		LunrApp lunrApp = new LunrApp();
		this.documentoController = lunrApp.getDocumentoController();
		this.buscaController = lunrApp.getBuscaController();
		this.similaridadeController = lunrApp.getSimilaridadeController();
		this.apresentacaoController = lunrApp.getApresentacaoController();
		var exemplo = new DocumentoExemplos();
		this.documentoController.adicionaDocumentoHtml(HTML_ID, exemplo.sampleHTML());
		this.documentoController.adicionaDocumentoJava(JAVA_ID, exemplo.sampleJava());
		this.documentoController.adicionaDocumentoTxt(TEXTO1_ID, "um arquivo! texto simples.\r\nuse DUAS linhas apenas.");
		this.documentoController.adicionaDocumentoTxt(TEXTO2_ID, "um arquivo! texto simples.\r\nuse TRÊS linhas agora.\r\nMAIS AVANÇO!");
		this.documentoController.adicionaDocumentoTxt(CASA01_ID, "Uma casa feliz é uma casa bonita");
		this.documentoController.adicionaDocumentoTxt(CASA02_ID, "Um dia feliz é um bom dia");
		this.documentoController.adicionaDocumentoTxt(EXEMPLO01_ID, "Uma criança brincando de adedonha");
		
		
		
		



	
		
	
}
}
