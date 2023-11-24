import java.util.List;

public class PessoaService {
	private PessoaRepository pr;

	public PessoaService(PessoaRepository pr) {
		this.pr = pr;
	}

	public void cadastraPessoa(Pessoa pessoa) {
		if (pr.existePessoa(pessoa.getCpf())) {
			throw new IllegalArgumentException("Usuário já cadastrado!");
		}
		this.pr.cadastraPessoa(pessoa);

	}

	public void atualizarIdade(String cpf, int novaIdade) {
		Pessoa pessoa = this.pr.buscarPorCpf(cpf);
		pessoa.setIdade(novaIdade);

	}

	public void atualizarTelefone(String cpf, String novoTelefone) {
		Pessoa pessoa = this.pr.buscarPorCpf(cpf);
		pessoa.setTelefone(novoTelefone);

	}

	public void atualizarEndereco(String cpf, String novoEndereco) {
		Pessoa pessoa = this.pr.buscarPorCpf(cpf);
		pessoa.atualizarEndereco(novoEndereco);
		// pessoa.adicionarEndereco(novoEndereco);

	}

	public void atualizarProfissao(String cpf, String novaProfissao) {
		Pessoa pessoa = this.pr.buscarPorCpf(cpf);
		pessoa.setProfissao(novaProfissao);

	}

	public void removerPessoa(String cpf) {
		this.pr.removerPessoa(cpf);

	}

	public List<Pessoa> listarPessoas() {
		return pr.listarPessoas();
	}

	public void removerTelefone(String cpf) {
		Pessoa pessoa = pr.buscarPorCpf(cpf);
		pessoa.setTelefone("Nenhum telefone cadastrado!");
	}

	public void removeEndereco(String cpf, String endereco) {
		Pessoa pessoa = pr.buscarPorCpf(cpf);
		pessoa.removeEndereco(endereco);
	}

	public void removerProfissao(String cpf) {
		Pessoa pessoa = pr.buscarPorCpf(cpf);
		pessoa.setProfissao("Nenhuma profissão cadastrada!");

	}
}