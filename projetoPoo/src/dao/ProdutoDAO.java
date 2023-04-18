package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


import classes.Produto;
import conexao.Conexao;

public class ProdutoDAO {

	private Conexao conexao;
	private String schema;

	PreparedStatement pInclusaoProduto = null;

	public ProdutoDAO(Conexao conexao, String schema) {
		this.conexao = conexao;
		this.schema = schema;
		prepararSqlInclusaoProduto();
	}

	private void prepararSqlInclusaoProduto() {
		String sql = "insert into " + this.schema + ".produto";
		sql = sql + " (idProduto, descricao, vlCusto, vlVenda, categoria)";
		sql = sql + " values ";
		sql = sql + " (?, ?, ?, ?, ?)";

		try {
			pInclusaoProduto = conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
		}
	}

	public void alterarProduto(Produto produto) {
		String sql = "UPDATE produto SET descricao=?, vlcusto=?, vlvenda=?, categoria=? WHERE id=?"
				+ "where idProduto = " + produto.getIdProduto();
		conexao.query(sql);
	}

	public void listaProdutos() {
		ResultSet tabela;

		String sql = "select * from " + this.schema + ".produto order by idProduto";

		tabela = conexao.query(sql);

		try {
			tabela.last();
			int rowCount = tabela.getRow();
			System.out.println("Quantidade de produtos: " + rowCount);

			if (rowCount > 0) {
				System.out.println("\nCódigo\tDescrição\tvlCusto\tvlCusto");
			} else {
				System.out.println("\nNão possui dados.");
				return;
			}

			tabela.beforeFirst();

			while (tabela.next()) {
				System.out.printf("%s\ts\t%d\t%d\n", tabela.getInt("idProduto"), tabela.getString("descricao"),
						tabela.getDouble("vlCusto"), tabela.getDouble("vlCusto"));
			}

		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public void deletarProduto(int idProduto) {
		String sql = "delete from " + this.schema + ".produto" + " where idProduto = " + idProduto;
		conexao.query(sql);
	}

}
