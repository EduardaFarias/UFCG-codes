package sapo.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sapo.Facade;

public class TarefaTest {

	private Facade facade = new Facade();

	@BeforeEach
	void setUp() {
		this.facade = new Facade();
		facade.cadastrarAtividade("estudar p2", "modelagem", "111111111-11");
		facade.cadastraPessoa("123456789-00", "luiza", new String[] { "dança", "canta" });
	}

	@Test
	public void testCadastraTarefa() {
		String[] habilidades = { "rayane é chata", "cansei de cc" };
		this.facade.cadastrarTarefa("STD-0", "programação", habilidades);
		String descricao = "Atividade de estudo de OO, considerando alunos com experiência de programação e uso da linguagem Java.";

	}

	@Test
	void testCadastraAtividade() {
		String[] habilidades = { "rayane é chata", "cansei de cc" };
		String descricao = "Atividade de estudo de OO, considerando alunos com experiência de programação e uso da linguagem Java.";
		Assertions.assertEquals("STD-1", this.facade.cadastrarAtividade("Estudar OO", descricao, "123.456.45-90"));

	}

	@Test
	public void testAlteraNomeTarefa() {
		String idTarefa = facade.cadastrarTarefa("STD-0", "programação",
				new String[] { "revisar herança", "modeligado" });
		facade.alterarNomeTarefa(idTarefa, "fmcc");
	}

	@Test
	public void testAlterarHabilidadesTarefa() {
		String idTarefa = facade.cadastrarTarefa("STD-0", "programação",
				new String[] { "revisar herança", "modeligado" });
		facade.alterarHabilidadesTarefa(idTarefa, new String[] { "ler mais", "dormir" });
		assertTrue(facade.exibirTarefa(idTarefa).contains("dormir"));
		assertTrue(facade.exibirTarefa(idTarefa).contains("ler mais"));
	}

	@Test
	public void testAdicionaHorasTarefa() {
		String idTarefa = facade.cadastrarTarefa("STD-0", "programação",
				new String[] { "revisar herança", "modeligado" });
		facade.adicionarHorasTarefa(idTarefa, 4);
		assertTrue(facade.exibirTarefa(idTarefa).contains("4 hora(s)"));

	}

	@Test
	public void testRemoverHorasTarefa() {
		String idTarefa = facade.cadastrarTarefa("STD-0", "programação",
				new String[] { "revisar herança", "modeligado" });
		facade.adicionarHorasTarefa(idTarefa, 4);
		facade.removeHorasTarefa(idTarefa, 2);
		assertTrue(facade.exibirTarefa(idTarefa).contains("2 hora(s)"));
	}

	@Test
	public void testConcluirAtividade() {
		String idTarefa = facade.cadastrarTarefa("STD-0", "programação",
				new String[] { "revisar herança", "modeligado" });
		facade.concluirTarefa(idTarefa);
	}

	@Test
	public void testRemoverTarefa() {
		String idTarefa = facade.cadastrarTarefa("STD-0", "programação",
				new String[] { "revisar herança", "modeligado" });
		facade.removeTarefa(idTarefa);
		assertThrows(Exception.class, () -> facade.exibirTarefa(idTarefa));
	}

	@Test
	public void testAssociarPessoaTarefa() {
		String idTarefa = facade.cadastrarTarefa("STD-0", "programação",
				new String[] { "revisar herança", "modeligado" });
		facade.associarPessoaTarefa("123456789-00", idTarefa);
	}

	@Test
	public void testRemovePessoaTarefa() {
		String idTarefa = facade.cadastrarTarefa("STD-0", "programação",
				new String[] { "revisar herança", "modeligado" });
		facade.associarPessoaTarefa("123456789-00", idTarefa);
		facade.removerPessoaTarefa("123456789-0", idTarefa);
	}
}
