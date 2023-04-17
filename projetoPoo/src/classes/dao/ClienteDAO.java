package classes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import classes.Cliente;
import conexao.Conexao;

public class ClienteDAO {
	// Crio o atributo de conex�o que receber� a conex�o do sistema
	private Conexao conexao;
	// Crio o atributo schema que receber� a conex�o do sistema
	private String schema;

	PreparedStatement pInclusao = null;

	// Construtor da classe que recebe a conex�o e o schema
	public ClienteDAO(Conexao conexao, String schema) {
		this.conexao = conexao;
		this.schema = schema;
		prepararSqlInclusao();
	}

	private void prepararSqlInclusao() {
		String sql = "insert into " + this.schema + ".cliente";
		sql += " (nome, cpf, endereco, dtNascimento)";
		sql += " values ";
		sql += " (?, ?, ?, ?)";

		try {
			pInclusao = conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
			// System.err.println(e);
			// e.printStackTrace();
		}
	}

	public int incluirCliente(Cliente cliente) {
		try {
			pInclusao.setString(1, cliente.getNome());
			pInclusao.setString(2, cliente.getCpf());
			pInclusao.setString(3, cliente.getEndereco());

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

	public void alterarCliente(Cliente cliente) {
		String sql = "update " + this.schema + ".cliente set " + "nome = '" + cliente.getNome() + "'" + ", cpf = '"
				+ cliente.getCpf() + "'" + ", endereco = '" + cliente.getEndereco() + "'" + "' " + "where idcliente = "
				+ cliente.getIdCliente();
		conexao.query(sql);
	}

	// M�todo que busca um cliente a partir do seu idcliente (c�digo)
	// Retorna para quem chamou um objeto Cliente com todos os dados do cliente
	// encontrado
	public Cliente selecionarCliente(int idCliente) {
		Cliente cliente = new Cliente();
		ResultSet tabela;

		String sql = "select * from " + this.schema + ".cliente where idcliente = " + idCliente;

		tabela = conexao.query(sql);

		try {
			// tabela.next vai para o pr�xima linha da tabela
			// o result come�a antes da primeira linha
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

	public void apagarCliente(int idCliente) {
		String sql = "delete from " + this.schema + ".cliente" + " where idcliente = " + idCliente;

		conexao.query(sql);
	}

	public Cliente localizarCliente(String nome, int idCliente) {
		Cliente cliente = new Cliente();

		String sql;
		ResultSet tabela;

		if (nome == null) {
			sql = "select * from " + this.schema + ".cliente where idcliente = " + idCliente;
		} else
			sql = "select * from " + this.schema + ".cliente where nome = '" + nome + "'";

		tabela = conexao.query(sql);

		try {
			// tabela.next vai para a pr�xima linha da tabela
			// o ResultSet come�a antes da primeira linha
			if (tabela.next()) {
				cliente.setIdCliente(tabela.getInt("idcliente"));
				cliente.setNome(tabela.getString("nome"));
				cliente.setCpf(tabela.getString("cpf"));
				cliente.setEndereco(tabela.getString("endereco"));
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
			tabela.last(); // segue para a �ltima linha da tabela
			int rowCount = tabela.getRow(); // pega o n�mero da linha (que no caso � a �ltima)
			System.out.println("Quantidade de clientes: " + rowCount);

			if (rowCount > 0) {
				System.out.println("\nCódigo\tNome\t\t\tCPF\t\tEndereço");
			} else {
				System.out.println("\nN�o possui dados.");
				return; // Aqui chamo o return para sair do m�todo
						// O return � void (vazio)
			}

			tabela.beforeFirst();

			while (tabela.next()) {
				System.out.printf("%s\t%-20s\t%s\t%s\n", tabela.getInt("idcliente"), tabela.getString("nome"),
						tabela.getString("cpf"), tabela.getString("endereco"));
			}

		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
}
