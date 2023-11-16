package sapo.controllers;

import sapo.services.TarefaGerencialService;
public class TarefaGerencialController {

	private TarefaGerencialService tgs;
	
	public TarefaGerencialController(TarefaGerencialService tgs) {
		this.tgs = tgs;
	}
	
	public String cadastrarTarefaGerencial(String atividadeId, String nome, String[] habilidades, String[] idTarefas) {
		return this.tgs.cadastrarTarefaGerencial(atividadeId, nome, habilidades, idTarefas);
	}
}
