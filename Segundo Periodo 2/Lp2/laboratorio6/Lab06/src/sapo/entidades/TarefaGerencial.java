package sapo.entidades;

public class TarefaGerencial {
	private String nome;
	private String[] habilidades;
	int horasGastas;
	private String idTarefa;
	private boolean concluida;
	public TarefaGerencial(String nome, String[] habilidades) {
		this.nome= nome;
		this.habilidades = habilidades;
		this.horasGastas = 0;
		this.concluida = false;
	}
}
