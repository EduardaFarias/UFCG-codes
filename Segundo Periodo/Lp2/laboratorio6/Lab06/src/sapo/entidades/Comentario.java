package sapo.entidades;

import java.time.LocalDateTime;

public class Comentario {

	private Pessoa autor;
	private String comentario;
	private LocalDateTime data;

	public Comentario(Pessoa autor, String comentario) {
		this.autor = autor;
		this.comentario = comentario;
		this.data = LocalDateTime.now();
	}

	@Override
	public String toString() {
		String saida = "-- " + comentario + "(" + autor.getNome() + ")\n";
		return saida;
	}

	
	public Pessoa getAutor() {
		return autor;
	}

}
