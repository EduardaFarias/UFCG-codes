package sapo.controllers;


import sapo.entidades.VerificacaoGeral;
import sapo.services.TarefaService;

public class TarefaController {
	
	private VerificacaoGeral vg;
	private TarefaService ts;
	

	public TarefaController(TarefaService ts) {
		this.vg = new VerificacaoGeral();
		this.ts = ts;
		this.vg = new VerificacaoGeral();
	}

	public String cadastrarTarefa(String atividadeId, String nome, String[] habilidades) {
		this.vg.verificaAtividadeId(atividadeId);
		this.vg.verificaNome(nome);
		this.vg.verificaHabilidades(habilidades);
        return this.ts.cadastrarTarefa(atividadeId, nome, habilidades);

	}

	public void alterarNomeTarefa(String idTarefa, String novoNome) {
		this.vg.verificaAtividadeId(novoNome);
		this.vg.verificaNome(novoNome);
		this.ts.alterarNomeTarefa(idTarefa, novoNome);

	}

	public void alterarHabilidadesTarefa(String idTarefa, String[] habilidades) {
		this.vg.verificaAtividadeId(idTarefa);
		this.vg.verificaHabilidades(habilidades);
		this.ts.alterarHabilidadesTarefa(idTarefa, habilidades);

	}

	public void adicionaHorasTarefa(String idTarefa, int horas) {
		this.vg.verificaAtividadeId(idTarefa);
		this.ts.adicionaHorasTarefa(idTarefa, horas);

	}

	public void removerHorasTarefa(String idTarefa, int horas) {
		this.vg.verificaAtividadeId(idTarefa);
		this.ts.removerHorasTarefa(idTarefa,horas);

	}

	public void concluirTarefa(String idTarefa) {
		this.vg.verificaAtividadeId(idTarefa);
		this.ts.concluirTarefa(idTarefa);

	}

	public void removeTarefa(String idTarefa) {
		this.ts.removerTarefa(idTarefa);

	}

	public String exibirTarefa(String idTarefa) {
		return this.ts.exibirTarefa(idTarefa);

	}

	public void associarPessoaTarefa(String cpf, String idTarefa) {
		this.vg.verificaCpf(cpf);
		this.vg.verificaAtividadeId(idTarefa);
		this.ts.associarPessoaTarefa(cpf, idTarefa);

	}

	public void removerPessoaTarefa(String cpf, String idTarefa) {
		this.vg.verificaCpf(cpf);
		this.ts.removerPessoaTarefa(cpf, idTarefa);

	}

}

