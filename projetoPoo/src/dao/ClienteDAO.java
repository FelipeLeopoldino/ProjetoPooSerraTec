package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import classes.Cliente;
import conexao.Conexao;

public class ClienteDAO {
	private Conexao conexao;
	private String schema;

	PreparedStatement pInclusao = null;

	public ClienteDAO(Conexao conexao, String schema) {
		this.conexao = conexao;
		this.schema = schema;
		prepararSqlInclusao();
	}

	private void prepararSqlInclusao() {
		String sql = "insert into " + this.schema + ".cliente";
		sql += " (idcliente, nome, cpf, endereco, dtnascimento, telefone)";
		sql += " values ";
		sql += " (?,?,?,?,?,?)";

		try {
			pInclusao = conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
		}
	}

	public int incluirCliente(Cliente cliente) {
		try {
			pInclusao.setString(1, cliente.getNome());
			pInclusao.setString(2, cliente.getCpf());
			pInclusao.setString(3, cliente.getEndereco());
			pInclusao.setString(4, cliente.getDtNascimento());
			pInclusao.setString(5, cliente.getTelefone());

			return pInclusao.executeUpdate();
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nCliente não incluído.\nVerifique se foi chamado o conect:\n" + e);
			} else {
				System.err.println(e);
				e.printStackTrace();
			}
			return 0;
		}

	}

//	public void alterarCliente(Cliente cliente) {
//		String sql = "update " + this.schema + ".cliente set " + "nome = '" + cliente.getNome() + "'" + ", cpf = '"
//				+ cliente.getCpf() + "'" + ", dtNascimento = '" + cliente.getDtNascimento() + "'" + ", endereco = '"
//				+ cliente.getEndereco() + "'" + "' " + "where idcliente = " + cliente.getIdCliente();
//		conexao.query(sql);
//	}

	public Cliente selecionarCliente(int idCliente) {
		Cliente cliente = new Cliente();
		ResultSet tabela;

		String sql = "select * from " + this.schema + ".cliente where idcliente = " + idCliente;

		tabela = conexao.query(sql);

		try {
			if (tabela.next()) {
				cliente.setIdCliente(tabela.getInt("idcliente"));
				cliente.setNome(tabela.getString("nome"));
				cliente.setCpf(tabela.getString("cpf"));
				cliente.setEndereco(tabela.getString("endereco"));
			} else
				System.out.println("IdCliente " + idCliente + " não localizado.");

			tabela.close();
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}

		return cliente;
	}

//	public void apagarCliente(int idCliente) {
//		String sql = "delete from " + this.schema + ".cliente" + " where idcliente = " + idCliente;
//
//		conexao.query(sql);
//	}

	public Cliente localizarCliente(String nome, int idCliente) {
		Cliente cliente = new Cliente();

		String sql = "select * from " + this.schema + ".cliente";
		ResultSet tabela;
		tabela = conexao.query(sql);

		if (nome == null) {
			sql = "select * from " + this.schema + ".cliente where idcliente = '" + idCliente + "'";

		} else
			sql = "select * from " + this.schema + ".cliente where nome = '" + nome + "'";

		tabela = conexao.query(sql);

		try {
			if (tabela.next()) {
				cliente.setIdCliente(tabela.getInt("idcliente"));
				cliente.setNome(tabela.getString("nome"));
				cliente.setCpf(tabela.getString("cpf"));
				cliente.setEndereco(tabela.getString("endereco"));
				cliente.setDtNascimento(tabela.getString("dtnascimento"));
				
				System.out.printf("Nome: %s CPF: %s Endereço: %s DataNascimento: %s\n", cliente.getNome(),
						cliente.getCpf(), cliente.getEndereco(), cliente.getDtNascimento());
				
			} else {
				if (nome == null) {
					System.out.println("IdCliente " + idCliente + " não localizado.");
				} else
					System.out.println("Cliente '" + nome + "' não localizado.");

				cliente = null;
			}

			tabela.close();
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}

		return cliente;
	}

	public void listarClientes() {
		ResultSet tabela;

		String sql = "select * from " + this.schema + ".cliente order by idcliente";

		tabela = conexao.query(sql);

		try {
			tabela.last();
			int rowCount = tabela.getRow();
			System.out.println("Quantidade de clientes: " + rowCount);

			if (rowCount > 0) {
				System.out.println("\nCódigo\tNome\t\tCPF\t\tdtNascimento\tEndereço");
			} else {
				System.out.println("\nNão possui dados.");
				return;
			}

			tabela.beforeFirst();

			System.out.println();
			while (tabela.next()) {
				System.out.printf("%d\t%.15s\t%s\t%s\t%s\n", tabela.getInt("idcliente"), tabela.getString("nome"),
						tabela.getString("cpf"), tabela.getString("dtnascimento"), tabela.getString("endereco"));
			}
			System.out.println();

		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
}
