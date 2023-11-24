public class Bootstrap {
	public static void main(String[] args) {
		// Criação das instâncias das classes necessárias
		PessoaRepository pr = new PessoaRepository();
		PessoaService ps = new PessoaService(pr);
		PessoaController pc = new PessoaController(ps);

		// Criando pessoas

		Pessoa pessoa1 = new Pessoa("Ely", 22, "123.989.587-60", "8899-6745", "Rua: Epaminondas Macaxeira, 387",
				"Programador");
		Pessoa pessoa2 = new Pessoa("Canella", 1, "568.786.333.10", "3322-2735", "Rua: Maria Aparecida Silva, 130",
				"Não possui");
		Pessoa pessoa3 = new Pessoa("Deyse", 27, "559.362.994-04", "9829-3218", "Rua: Eduardo Firmino Costa",
				"Psicóloga");
		Pessoa pessoa4 = new Pessoa("Uriel", 22, "123-456-789-10", "5599-5818", "Rua: Marecos da vila", "Fotógrafo");
		pc.cadastraPessoa(pessoa1);
		pc.cadastraPessoa(pessoa2);
		pc.cadastraPessoa(pessoa3);
		pc.listarPessoas();

		// Atualizando dados da pessoa 1
		pc.atualizarIdade("123.989.587-60", 23);
		pc.atualizarTelefone("123.989.587-60", "3333-6699");
		pc.atualizarEndereco("123.989.587-60", "Rua: Julio eduardo costa, 33");
		pc.atualizarProfissao("123.989.587-60", "Viajante");
		// System.out.println(pessoa1.toString());

		// Removendo dados da pessoa 2
		pc.removerTelefone("568.786.333.10");
		pc.removeEndereco("568.786.333.10", "Rua: Maria Aparecida Silva, 130");
		pc.removerProfissao("568.786.333.10");
		System.out.println(pessoa2.toString());

		// Pessoa3 contina do mesmo jeito

		// Removendo pessoa 4
		pc.listarPessoas();
		pc.removerPessoa("123-456-789-10");
		pc.listarPessoas();

	}
}
