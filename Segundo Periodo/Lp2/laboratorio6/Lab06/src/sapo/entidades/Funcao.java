package sapo.entidades;

import java.util.Arrays;

public class Funcao implements FuncaoInterface {

	private String funcao;
	private Pessoa pessoa;

	public Funcao() {
		this.funcao = null;
	}

	@Override
	public String ToString() {
		Arrays.sort(pessoa.getHabilidades());
		String habi = "\n";
		for (String i : pessoa.getHabilidades()) {
			habi += "- " + i;
		}
		String mensagem = this.pessoa.getNome() + " " + this.pessoa.getCpf() + System.lineSeparator() + this.funcao
				+ System.lineSeparator() + habi.trim();
		return mensagem;

	}

	@Override
	public void removerFuncao(String cpf) {
		this.funcao = null;

	}

	@Override
	public String[] listarPessoas(String cpf) {
		return null;

	}
	@Override
	public void pegarNivel(String cpf) {
		// TODO Auto-generated method stub

	}

	@Override
	public String funcao() {
		return this.funcao = null;
	}

	
	

}
