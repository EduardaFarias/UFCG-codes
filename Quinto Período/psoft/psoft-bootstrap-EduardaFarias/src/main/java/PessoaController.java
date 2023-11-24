import java.util.List;

public class PessoaController {
	private PessoaService ps;

	public PessoaController(PessoaService ps) {
		this.ps = ps;
	}

	public void cadastraPessoa(Pessoa pessoa) {
		if (pessoa.getNome() == null || pessoa.getNome().isEmpty()) {
			throw new IllegalArgumentException("Nome está vazio");
		}
		if (pessoa.getCpf() == null || pessoa.getCpf().isEmpty()) {
			throw new IllegalArgumentException("Cpf está vazio");
		}
		this.ps.cadastraPessoa(pessoa);
	}

	public void atualizarIdade(String cpf, int novaIdade) {
		this.ps.atualizarIdade(cpf, novaIdade);
	}

	public void atualizarTelefone(String cpf, String novoTelefone) {
		this.ps.atualizarTelefone(cpf, novoTelefone);
	}

	public void atualizarEndereco(String cpf, String novoEndereco) {
		this.ps.atualizarEndereco(cpf, novoEndereco);
	}

	public void atualizarProfissao(String cpf, String novaProfissao) {
		this.ps.atualizarProfissao(cpf, novaProfissao);
	}

	public void removerPessoa(String cpf) {
		this.ps.removerPessoa(cpf);
	}

	public void listarPessoas() {
		List<Pessoa> pessoasCadastradas = ps.listarPessoas();

		if (pessoasCadastradas.isEmpty()) {
			System.out.println("Não há pessoas cadastradas.");
		} else {
			System.out.println("Pessoas cadastradas:");
			for (Pessoa pessoa : pessoasCadastradas) {
				System.out.println(pessoa.toString());
				System.out.println("------------------------------");
			}
		}
	}

	public void removerTelefone(String cpf) {
		this.ps.removerTelefone(cpf);
	}

	public void removeEndereco(String cpf, String endereco) {
		this.ps.removeEndereco(cpf, endereco);
	}

	public void removerProfissao(String cpf) {
		this.ps.removerProfissao(cpf);
	}
}
