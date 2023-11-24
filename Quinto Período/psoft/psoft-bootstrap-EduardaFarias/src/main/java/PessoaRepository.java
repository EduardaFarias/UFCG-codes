import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PessoaRepository {
	private HashMap<String, Pessoa> pessoas; // armazena a pessoa pelo cpf e o objeto pessoa

	public PessoaRepository() {
		this.pessoas = new HashMap<>();
	}

	public boolean existePessoa(String cpf) {
		return this.pessoas.containsKey(cpf);
	}

	public void cadastraPessoa(Pessoa pessoa) {
		this.pessoas.put(pessoa.getCpf(), pessoa);
	}

	public Pessoa buscarPorCpf(String cpf) {
		return pessoas.get(cpf);
	}

	public void removerPessoa(String cpf) {
		pessoas.remove(cpf);
	}

	public List<Pessoa> listarPessoas() {
		return new ArrayList<>(pessoas.values());
	}

}
