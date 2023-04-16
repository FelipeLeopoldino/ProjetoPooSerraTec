package classes;

import java.util.Date;

public abstract class Pessoa {

	protected int id;
	protected String nome;
	protected String cpf;
	protected Date dtNascimento;
	
	
	public Pessoa(int id, String nome, String cpf, Date dtNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dtNascimento = dtNascimento;
	}

	public abstract String toString();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

}
