package sapo.entidades;

public class VerificacaoGeral {

	public void verificaCpf(String cpf) {
		if (cpf.isBlank()) {
			throw new IllegalArgumentException("cpf é vazio");
		}
	 }
	public void verificaAtividadeId(String atividadeId) {
		if (atividadeId.isBlank() ) {
			throw new IllegalArgumentException("atividadeId esta vazio");
		}
	}
	
	public void verificaNome(String nome) {
		if (nome.isBlank() ) {
			throw new IllegalArgumentException("nome esta vazio");
		}
	}
	
	public void verificaHabilidades(String[] habilidades) {
		if (habilidades == null) {
			throw new IllegalArgumentException("habilidades esta nula");
		}
	}

	public void verificaPessoaExiste(String nome) {
		if (nome.isBlank()) {
			throw new IllegalArgumentException("Pessoa não existe");
		}
	}
}
