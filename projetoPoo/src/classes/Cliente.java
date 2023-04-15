package classes;

import java.util.Date;

public class Cliente {

	private int idCliente;
	private String nome;
	private String cpf;
	private Date dtNascimento;
	private String endereco;
	private String telefone;

	public Cliente(int idCliente, String nome, String cpf, Date dtNascimento, String endereco, String telefone) {
		this.idCliente = idCliente;
		this.nome = nome;
		this.cpf = cpf;
		this.dtNascimento = dtNascimento;
		this.endereco = endereco;
		this.telefone = telefone;
	}

	// getters e setters
	public int getidCliente() {
		return idCliente;
	}

	public void setidCliente(int idCliente) {
		this.idCliente = idCliente;
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

	public Date getdtNascimento() {
		return dtNascimento;
	}

	public void setdtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
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
