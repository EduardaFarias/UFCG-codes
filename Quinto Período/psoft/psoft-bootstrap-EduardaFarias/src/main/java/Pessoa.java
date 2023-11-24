import java.util.ArrayList;
import java.util.List;

public class Pessoa {
	private String nome;
	private int idade;
	private String cpf;
	private String endereco;
	private String telefone;
	private List<String> enderecos;
	private String profissao;

	public Pessoa(String nome, int idade, String cpf, String telefone, String endereco, String profissao) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
		this.telefone = telefone;
		this.enderecos = new ArrayList<String>();
		this.endereco = endereco;
		this.enderecos.add(endereco);
		this.profissao = profissao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<String> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<String> enderecos) {
		this.enderecos = enderecos;
	}

	@Override
	public String toString() {
		return "Nome: " + this.getNome() + "\nIdade: " + this.getIdade() + "\nCpf: " + this.getCpf()
				+ "\nEndereço(s):\n" + this.exibirEnderecos() + "\nTelefone: " + this.getTelefone() + "\nProfissão: "
				+ this.getProfissao();
	}

	public void atualizarEndereco(String EnderecoNovo) {
		if (!enderecos.contains(EnderecoNovo)) {
			enderecos.add(EnderecoNovo);
		}
	}

	/**
	 * 
	 * Exibe todos os endereços em linhas separadas
	 */
	public String exibirEnderecos() {
		StringBuilder sb = new StringBuilder();
		for (String endereco : enderecos) {
			sb.append(endereco).append("\n");
		}
		return sb.toString();

	}

	/**
	 * Se tiver apenas 1 endereço ele apaga o mesmo, caso exista mais de um, ele
	 * procura o endereço passado e remove ele
	 * 
	 * @param endereco
	 */
	public void removeEndereco(String endereco) {
		if (enderecos.size() == 1) {
			enderecos.clear();
			enderecos.add("Nenhum endereço cadastrado!");
		} else {
			enderecos.remove(endereco);
		}

	}
}
