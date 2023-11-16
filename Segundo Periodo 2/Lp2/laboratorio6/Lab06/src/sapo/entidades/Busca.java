package sapo.entidades;

import java.util.Arrays;

public class Busca {

	private String tipo;
	private String[] resultado;

	public Busca(String tipo, String[] resultado) {
		this.tipo = tipo;
		this.resultado = resultado;
	}

	/*
	 * Método que retorna um array com o tipo da busca na primeira posição e em
	 * seguida os resultados da mesma busca.
	 */
	public String[] toArray() {
		String[] saida = new String[resultado.length + 1];
		saida[0] = tipo;
		for (int i = 0; i < resultado.length; i++) {
			saida[i + 1] = resultado[i];
		}
		return saida;
	}

	/*
	 * Método que retorna um array sendo representado em representação textual.
	 */

	@Override
	public String toString() {
		return Arrays.toString(toArray());
	}

}
