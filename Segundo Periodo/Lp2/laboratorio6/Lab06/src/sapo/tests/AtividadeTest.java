package sapo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import sapo.Facade;
import sapo.controllers.AtividadeController;
import sapo.services.AtividadeService;

class AtividadeTest {

	private Facade facade = new Facade();
	

	@Test
	void testCadastraAtividade() {
		String[] habilidades = { "rayane é chata", "cansei de cc" };
		this.facade.cadastraPessoa("129.456.45-90", "dudinha", habilidades);
		String descricao = "Atividade de estudo de OO, considerando alunos com experiência de programação e uso da linguagem Java.";
		Assertions.assertEquals("STD-0", this.facade.cadastrarAtividade("Estudar OO", descricao, "123.456.45-90"));

	}

	@Test
	void testMudarDescricao() {
		String[] habilidades = { "rayane é chata", "cansei de cc" };
		this.facade.cadastraPessoa("123.456.45-90", "dudinha", habilidades);
		String descricao = "Atividade de estudo de OO, considerando alunos com experiência de programação e uso da linguagem Java.";
		this.facade.cadastrarAtividade("estudar OO", descricao, "123.456.45-90");
		String novaDescricao = "atividade de estudo";
		this.facade.alterarDescricaoAtividade("STD-0", novaDescricao);
		Assertions.assertEquals(
				"STD-0: estudar OO\nResponsável: dudinha - 123.456.45-90\n===\natividade de estudo\n===Tarefas: 0/0)",
				this.facade.exibirAtividade("STD-0"));

	}

	@Test
	void testEncerrarAtividade() {
		String[] habilidades = { "rayane é chata", "cansei de cc" };
		this.facade.cadastraPessoa("123.456.45-90", "dudinha", habilidades);
		String descricao = "Atividade de estudo de OO, considerando alunos com experiência de programação e uso da linguagem Java.";
		this.facade.cadastrarAtividade("estudar OO", descricao, "123.456.45-90");
		String novaDescricao = "atividade de estudo";
		this.facade.encerrarAtividade("STD-0");
		Assertions.assertThrowsExactly(IllegalStateException.class,
				() -> facade.alterarDescricaoAtividade("STD-0", novaDescricao));
	}

	@Test
	void testDesativarrAtividade() {
		String[] habilidades = {"fmcc2 ", "faz uns codigos feio"};
	    this.facade.cadastraPessoa("134.666.45-00", "carolina", habilidades);
	    String descricao = "Atividade de estudo de calculo, considerando alunos que ja desistiram de tudo.";
	    this.facade.cadastrarAtividade("largar cc ", descricao, "123.456.45-90");
	    this.facade.desativarAtividade("LRG-0");
	    String novaDescricao = "estudar nada";
	    Assertions.assertThrowsExactly(IllegalStateException.class, ()-> facade.alterarDescricaoAtividade("LRG-0", novaDescricao));
	    this.facade.reabrirAtividade("LRG-0");
	    this.facade.alterarDescricaoAtividade("LRG-0", novaDescricao);
	    
	    
    }
	
	@Test
	void testaTarefa() {
		this.facade.cadastrarAtividade("estudar p2", "modelagem", "122.345.678-90");
		String[] habilidades = {"fmcc2 ", "faz uns codigos feio"};
		this.facade.cadastrarTarefa("STD-0", "estudando", habilidades);
		
	}
	

	@Test
	void testMudaResponsavelAtividade() {
		this.facade.cadastrarAtividade("estudar p2", "modelagem", "122.345.678-90");
		String[] habilidades = {"fmcc2 ", "faz uns codigos feio"};
		this.facade.alterarResponsavelAtividade("STD-0", "123.567.909-00");
	}
	
	


}
