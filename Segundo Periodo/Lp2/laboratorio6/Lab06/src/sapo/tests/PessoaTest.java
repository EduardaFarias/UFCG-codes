package sapo.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import sapo.Facade;
import sapo.controllers.PessoaController;
import sapo.services.PessoaService;

class PessoaTest {

    private Facade facade = new Facade();
    
    
    
    @Test
    void testCadastraPessoa() {
        String[] habilidades = {"rayane é chata", "cansei de cc"};
        this.facade.cadastraPessoa("123.456.45-90", "dudinha", habilidades);
    }

    @Test
    void testAlterarNomePessoa() {
    	String[] habilidades = {"rayane é chata", "cansei de cc"};
        this.facade.cadastraPessoa("123.456.45-90", "dudinha", habilidades);
        this.facade.alteraNomePessoa("123.456.45-90","erika");
    }
	
   @Test
   	void testExibirPessoa() {
	   String[] habilidades = {"Sabe cozinhar", "sabe fazer testes"};
	   this.facade.cadastraPessoa("111.111.111-33", "camila", habilidades);
	   Assertions.assertEquals("camila - 111.111.111-33\n- Sabe cozinhar\n- sabe fazer testes\nComentários:", this.facade.exibePessoa("111.111.111-33"));
   }
   
   @Test
   void testAlterarHabilidesPessoa() {
	   String[] habilidades = {"Sabe jogar ping pong", "sabe escrever"};
       this.facade.cadastraPessoa("123.456.45-90", "dudinha", habilidades);
       String[] novasHabilidades = {"Sabe programar em C", "Sabe queimar cafeteiras"};
       this.facade.alteraHabilidadesPessoa("123.456.45-90", novasHabilidades);
   }
   
   @Test
   void testRemoverPessoa() {
	   String[] habilidades = {"Sabe cantar", "digita rápido"};
	   this.facade.cadastraPessoa("101.992.334.62", "Joao", habilidades);
	   this.facade.removePessoa("101.992.334.62");
   }
   
   @Test
   void testAdicionarComentario() {
	  String comentario = "é uma boa pessoa";
	  String[] habilidades1 = {"Sabe cantar", "digita rápido"};
	  this.facade.cadastraPessoa("332.888.777-32", "Pessoa1", habilidades1);
	  String[] habilidades2 = {"Sabe debuggar", "Trabalhar em equipe"};
	   this.facade.cadastraPessoa("061.584.332-44", "Joao", habilidades2);
	  this.facade.adicionaComentarioPessoa("332.888.777-32", "é uma boa pessoa", "061.584.332-44");
   }
   
   
   
   
   
   
}
