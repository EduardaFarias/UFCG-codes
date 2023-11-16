package sapo.services;

import java.util.NoSuchElementException;

import sapo.entidades.Pessoa;
import sapo.entidades.Tarefa;

public class TarefaService {

	private AtividadeService as;
	private PessoaService ps;

	public TarefaService(PessoaService ps, AtividadeService as) {
		this.ps = ps;
		this.as = as;

	}

	public String cadastrarTarefa(String atividadeId, String nome, String[] habilidades) {
		return this.as.cadastrarTarefa(atividadeId, nome, habilidades);
	}

	public void alterarNomeTarefa(String idTarefa,String novoNome) {
		Tarefa tarefa = this.as.getTarefa(idTarefa);

		if (tarefa == null) {
			throw new NoSuchElementException();
		}
		if (tarefa.isConcluida()) {
			throw new IllegalStateException();
		}

		tarefa.alterarNomeTarefa(novoNome);

	}

	public void alterarHabilidadesTarefa(String idTarefa, String[] habilidades) {
		Tarefa tarefa = this.as.getTarefa(idTarefa);

		if (tarefa == null) {
			throw new NoSuchElementException();
		}

		if (tarefa.isConcluida()) {
			throw new IllegalStateException();
		}

		tarefa.alteraHabilidadesTarefa(habilidades);

	}

	public void adicionaHorasTarefa(String idTarefa, int horas) {
		Tarefa tarefa = this.as.getTarefa(idTarefa);
		if (tarefa == null) {
			throw new NoSuchElementException();
		}
		if (tarefa.isConcluida()) {
			throw new IllegalStateException();
		}

		tarefa.adicionaHorasTarefa(horas);

	}

	public void removerHorasTarefa(String idTarefa, int horas) {
		Tarefa tarefa = this.as.getTarefa(idTarefa);
		if (tarefa == null) {
			throw new NoSuchElementException();
		}

		if (tarefa.isConcluida()) {
			throw new IllegalStateException();
		}
		tarefa.removerHorasTarefa(horas);

	}

	public void concluirTarefa(String idTarefa) {
		Tarefa tarefa = this.as.getTarefa(idTarefa);
		if (tarefa == null) {
			throw new NoSuchElementException();
		}
		if (tarefa.isConcluida()) {
			throw new IllegalStateException();
		}
		tarefa.concluir();

	}

	public void removerTarefa(String idTarefa) {
		
		Tarefa tarefa = this.as.getTarefa(idTarefa);
		if (tarefa == null) {
			throw new NoSuchElementException();
		}
		if (tarefa.isConcluida()) {
			throw new IllegalStateException();
		}
		this.as.removerTarefa(idTarefa);

	}

	public String exibirTarefa(String idTarefa) {
		Tarefa tarefa = this.as.getTarefa(idTarefa);
		if (tarefa == null) {
			throw new NoSuchElementException();
		}

		return tarefa.toString();

	}

	public void associarPessoaTarefa(String cpf, String idTarefa) {
		Pessoa pessoa = this.ps.getPessoa(cpf);
		if (pessoa == null) {
			throw new NoSuchElementException();
		}

		Tarefa tarefa = this.as.getTarefa(idTarefa);

		if (tarefa == null) {
			throw new NoSuchElementException();
		}

		if (tarefa.isConcluida()) {
			throw new IllegalStateException();
		}

		tarefa.associaPessoaTarefa(cpf);

	}

	public void removerPessoaTarefa(String cpf, String idTarefa) {
		Tarefa tarefa = this.as.getTarefa(idTarefa);

		if (tarefa == null) {
			throw new NoSuchElementException();
		}

		if (tarefa.isConcluida()) {
			throw new IllegalStateException();
		}

		tarefa.removerPessoaTarefa(cpf);

	}

}
