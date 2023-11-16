package sapo.entidades;

import java.util.Arrays;
import java.util.HashSet;

public class Tarefa {

	private int horasGastas;	
	private String nome;
	private String[] habilidades;
	private HashSet<String> cpfs;
	private String atividadeId;
	private String idTarefa;
	private boolean concluida;

	public Tarefa(String nome, String habilidades[]) {
		this.nome = nome;
		this.habilidades = habilidades;
		this.cpfs = new HashSet<>();
		this.horasGastas = 0;
		this.concluida = false;
	}

	@Override
	public String toString() {
		Arrays.sort(habilidades);
		String mensagem = "";
		mensagem = nome + "-" + idTarefa + "\n"+ " - " + Arrays.toString(habilidades) + "(" + horasGastas
				+ " hora(s) executada(s)\n" + "===\n" + "equipe:";
		return mensagem;
	}

	public String getIdAtividade() {
		return this.atividadeId;
	}

	public void associaPessoaTarefa(String cpf) {
		cpfs.add(cpf);

	}

	public void removerPessoaTarefa(String cpf) {
		cpfs.remove(cpf);

	}

	public void removerHorasTarefa(int horas) {
		this.horasGastas -= horas;
		if (horas < 0) {
			horas = 0;
		}

	}

	public void adicionaHorasTarefa(int horas) {
		this.horasGastas += horas;

	}

	public void alteraHabilidadesTarefa(String[] habilidades) {
		this.habilidades = habilidades;

	}

	public void alterarNomeTarefa(String novoNome) {
		this.nome = novoNome;

	}

	public String getIdTarefa() {
		return this.idTarefa;
	}

	public void concluir() {
		concluida = true;
	}

	public boolean isConcluida() {
		return concluida;
	}

	public String getNome() {
		return nome;
	}

	public int getOrdem() {
		return Integer.parseInt(idTarefa.split("-")[2]);
	}

	public String[] getHabilidades() {
		return this.habilidades;
	}

	public boolean hasPessoa() {
		return !this.cpfs.isEmpty();
	}
}
