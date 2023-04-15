package classes;

import java.util.Date;

public abstract class Cliente {

	private int idcliente;
	private String nome;
	private String cpf;
	private Date dtnascimento;
	private String endereco;
	private String telefone;

	public Cliente(int idcliente, String nome, String cpf, Date dtnascimento, String endereco, String telefone) {
		this.idcliente = idcliente;
		this.nome = nome;
		this.cpf = cpf;
		this.dtnascimento = dtnascimento;
		this.endereco = endereco;
		this.telefone = telefone;
	}

	// getters e setters
	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

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

	public Date getDtnascimento() {
		return dtnascimento;
	}

	public void setDtnascimento(Date dtnascimento) {
		this.dtnascimento = dtnascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
