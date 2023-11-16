package sapo.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import sapo.Facade;

public class FuncoesTest {
	private Facade facade = new Facade();

	@BeforeEach
	void setUp() {
		this.facade = new Facade();
		facade.cadastraAluno("123456789-11", "elida", "123", 5, new String[]{"Muito estudiosa", "degustatora de café"});;
	}
	
	@Test
	public void testCadastraAluno() {
		assertEquals(facade.exibePessoa("123456789-11"), "");
	}
}
