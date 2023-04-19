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

		String sql = "select * from " + this.schema + ".produto order by idproduto";

		tabela = conexao.query(sql);

		try {
			tabela.last();
			int rowCount = tabela.getRow();
			System.out.println("Quantidade de produtos: " + rowCount);

			if (rowCount > 0) {
				System.out.println("\ncodigo\t\tDescricao\t\tvlcusto\tvlvenda\tcategoria");
			} else {
				System.out.println("\nNão possui dados.");
				return;
			}

			tabela.beforeFirst();

			System.out.println();
			while (tabela.next()) {
				System.out.printf("%d\t%.20s\t\t%.2f\t%.2f\t%s\n", tabela.getInt("idproduto"), tabela.getString("descricao"),
						tabela.getDouble("vlcusto"), tabela.getDouble("vlvenda"), tabela.getString("categoria"));
			}
			System.out.println();

		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public void deletarProduto(int idProduto) {
		String sql = "delete from " + this.schema + ".produto" + " where idproduto = " + idProduto;
		conexao.query(sql);
	}
	
	
	
	public Produto localizarProduto(String descricao, int idProduto) {
		Produto produto = new Produto();
		
		String sql = "select * from " + this.schema + ".produto";
		ResultSet tabela;
		tabela = conexao.query(sql);

		if (descricao == null) {
			sql = "select * from " + this.schema + ".produto where idproduto = '" + idProduto + "'";

		} else
			sql = "select * from " + this.schema + ".produto where descricao = '" + descricao + "'";

		tabela = conexao.query(sql);

		try {
			if (tabela.next()) {
				produto.setIdProduto(tabela.getInt("idproduto"));
				produto.setDescricao(tabela.getString("descricao"));
				produto.setVlCusto(tabela.getDouble("vlcusto"));
				produto.setVlVenda(tabela.getDouble("vlvenda"));
				produto.setCategoria(tabela.getString("categoria"));
				
				System.out.printf("Descrição: %s vlCusto: %.2f vlVenda: %.2f Categoria: %s\n", produto.getDescricao(),produto.getVlCusto()
						,produto.getVlVenda(),produto.getCategoria());
				
			} else {
				if (descricao == null) {
					System.out.println("idProduto " + idProduto + " não localizado.");
				} else
					System.out.println("Produto '" + descricao + "' não localizado.");

				produto = null;
			}

			tabela.close();
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}

		return produto;
	}

	

}
