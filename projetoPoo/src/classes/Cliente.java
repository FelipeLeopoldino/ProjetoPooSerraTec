package classes;

import java.util.Date;

public class Cliente extends Pessoa {

	private int idCliente;
	private String endereco;
	private String telefone;
	

	public Cliente(int id, String nome, String cpf, Date dtNascimento, int idCliente, String endereco,
			String telefone) {
		super(id, nome, cpf, dtNascimento);
		this.idCliente = idCliente;
		this.endereco = endereco;
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", endereco=" + endereco + ", telefone=" + telefone + ", nome="
				+ nome + ", cpf=" + cpf + ", dtNascimento=" + dtNascimento + "]";
	}

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
