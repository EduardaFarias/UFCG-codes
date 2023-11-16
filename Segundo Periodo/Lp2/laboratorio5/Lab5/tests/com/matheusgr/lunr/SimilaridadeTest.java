package com.matheusgr.lunr;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SimilaridadeTest extends BaseTest {
	
	@Test
	void test01() {
		double teste01 = this.similaridadeController.similaridade(TEXTO1_ID, TEXTO2_ID);
		assertEquals(0.5, teste01);
	}
	
}
