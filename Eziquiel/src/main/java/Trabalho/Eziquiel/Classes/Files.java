package Trabalho.Eziquiel.Classes;
import java.io.Serializable;

public class Files implements Serializable{
	private static final long serialVersionUID = 7105596334842735479L;
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Files(String nome) {
		super();
		this.nome = nome;
	}
	
	
}
