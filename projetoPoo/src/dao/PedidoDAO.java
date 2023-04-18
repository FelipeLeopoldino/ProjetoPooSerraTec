package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import classes.Cliente;
import classes.PedidoItens;
import classes.Pedido;
import conexao.Conexao;

public class PedidoDAO {
	private Conexao conexao;
	private String schema;

	PreparedStatement pInclusaoPedido = null;
	PreparedStatement pInclusaoPedidoItem = null;

	public PedidoDAO(Conexao conexao, String schema) {
		this.conexao = conexao;
		this.schema = schema;
		prepararSqlInclusaoPedido();
	}

	private void prepararSqlInclusaoPedido() {
		String sql = "insert into " + this.schema + ".pedido";
		sql = sql + " (idPedido, data, valorTotal, idCliente)";
		sql = sql + " values ";
		sql = sql + " (?, ?, ?, ?)";

		try {
			pInclusaoPedido = conexao.getC().prepareStatement(sql);
		} catch (Exception e) {
		}
	}

	public int incluirPedido(Pedido pedido) {
		try {

			pInclusaoPedido.setInt(1, pedido.getidPedido());
			pInclusaoPedido.setDate(2, (Date) pedido.getdtEmissao());
			pInclusaoPedido.setDouble(3, pedido.getvalorTotal());
			pInclusaoPedido.setInt(4, pedido.getCliente().getIdCliente());

			return pInclusaoPedido.executeUpdate();
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nPedido não incluído.\nVerifique se foi chamado o conect:\n" + e);
			} else {
				System.err.println(e);
				e.printStackTrace();
			}
			return 0;
		}
	}

	public void alterarPedido(Pedido pedido) {
		String sql = "update " + this.schema + ".pedido set " + "numero = '" + pedido.getidPedido() + "'"
				+ ", idCliente = '" + pedido.getCliente().getIdCliente() + "'" + ", valorTotal = '"
				+ pedido.getvalorTotal() + "'" + "where idPedido = " + pedido.getidPedido();
		conexao.query(sql);
	}

	public Pedido selecionarPedido(String numero, int idpedido) {
		Pedido pedido = new Pedido();
		Cliente cliente = new Cliente();
		PedidoItens itens = new PedidoItens();

		ResultSet tbPedido, tbCliente, tbItens;

		String sql;

		if (numero == null) {
			sql = "select * from " + this.schema + ".pedido where idPedido = " + idpedido;
		} else
			sql = "select * from " + this.schema + ".pedido where numero = '" + numero + "'";

		tbPedido = conexao.query(sql);

		try {

			if (tbPedido.next()) {
				sql = "select * from " + this.schema + ".cliente where idcliente = " + tbPedido.getInt("idcliente");

				tbCliente = conexao.query(sql);

				if (tbCliente.next()) {
					cliente.setCpf(tbCliente.getString("cpf"));
					cliente.setEndereco(tbCliente.getString("endereco"));
					cliente.setNome(tbCliente.getString("nome"));
				}

				sql = "select p.*, pi.vlUnitario, pi.qtProduto from " + this.schema + ".produto p " + "right join "
						+ this.schema + ".pedidoItem pi on pi.idProduto = p.idProduto " + "where pi.idPedido = "
						+ tbPedido.getInt("idpedido");

				tbItens = conexao.query(sql);

				pedido.setidPedido(tbPedido.getInt("idPedido"));
				pedido.setCliente(cliente);
				pedido.setdtEmissao(tbPedido.getDate("data"));
				pedido.setvalorTotal(tbPedido.getDouble("valorTotal"));

				tbItens.beforeFirst();

				while (tbItens.next()) {
					itens.setDescricao(tbItens.getString("descricao"));
					itens.setIdProduto(tbItens.getInt("idproduto"));
					itens.setqtProduto(tbItens.getInt("quantidade"));
					itens.setvlUnitario(tbItens.getDouble("valorunitario"));
					itens.setVlCusto(tbItens.getDouble("custo"));
					itens.setVlVenda(tbItens.getDouble("preco"));

					pedido.adicionarItem(itens);
				}

				tbItens.close();
				tbCliente.close();
			} else
				System.out.println("IdPedido " + idpedido + " não localizado.");

			tbPedido.close();

		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}

		return pedido;
	}

	public void apagarPedido(int idpedido) {

		String sql = "delete from " + this.schema + ".pedidoItem" + " where idPedido = " + idpedido;

		conexao.query(sql);

		sql = "delete from " + this.schema + ".pedido" + " where idPedido = " + idpedido;

		conexao.query(sql);
	}

	public void listarPedidos() {
		ResultSet tabela;

		String sql = "select p.*, c.cpf, c.endereco, c.nome " + "from " + this.schema + ".pedido p " + "left join "
				+ this.schema + ".cliente c on c.idCliente = p.idCliente " + "order by idPedido";

		tabela = conexao.query(sql);

		try {
			tabela.last();
			int rowCount = tabela.getRow();
			System.out.println("Quantidade de pedidos: " + rowCount);

			if (rowCount > 0) {
				System.out.println("\nDADOS DO PEDIDO");
				System.out.println("---------------------------------------------------------------------");
				System.out.println("Número\t\tData\t\tCliente\tCPF\t\tValor Total");
			} else {
				System.out.println("\nNão possui dados.");
				return;
			}

			tabela.beforeFirst();

			while (tabela.next()) {
				System.out.printf("%s\t%s\t%-20s\t%s\t\t%s\t%2.2f\n", tabela.getString("numero"),
						tabela.getString("data"), tabela.getString("nome"), tabela.getString("cpf"),
						tabela.getString("valorTotal"));
			}

		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
}
