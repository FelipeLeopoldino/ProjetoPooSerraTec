package dao;

import java.sql.PreparedStatement;

import classes.Pedido;
import classes.PedidoItens;
import conexao.Conexao;

public class PedidoItensDAO {
	private Conexao conexao;
	private String schema;
	PreparedStatement pInclusaoPedidoItem = null;

	public PedidoItensDAO(Conexao conexao, String schema) {

		this.conexao = conexao;
		this.schema = schema;
		prepararSqlInclusaoPedidoItem();
	}

	private void prepararSqlInclusaoPedidoItem() {
		String sql = "insert into " + this.schema + ".peditem";
		sql = sql + " (idPedido, idProduto, vlUnitario, qtProduto)";
		sql = sql + " values ";
		sql = sql + " (?, ?, ?, ?)";

		try {
			pInclusaoPedidoItem = conexao.getC().prepareStatement(sql);
		} catch (Exception e) {

		}
	}

	public int incluirPedidoItem(Pedido pedido, PedidoItens itens) {
		try {
			pInclusaoPedidoItem.setInt(1, pedido.getidPedido());
			pInclusaoPedidoItem.setInt(2, itens.getIdProduto());
			pInclusaoPedidoItem.setDouble(3, itens.getvlUnitario());
			pInclusaoPedidoItem.setDouble(4, itens.getqtProduto());

			return pInclusaoPedidoItem.executeUpdate();
		} catch (Exception e) {
			if (e.getLocalizedMessage().contains("is null")) {
				System.err.println("\nPedidoItem não incluído.\nVerifique se foi chamado o conect:\n" + e);
			} else {
				System.err.println(e);
				e.printStackTrace();
			}
			return 0;
		}
	}

}
