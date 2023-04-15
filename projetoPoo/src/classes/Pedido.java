package classes;

import java.util.Date;

public class Pedido extends PedidoItens {

	private int idPedido;
	private Date dtEmissao;
	private Date deEntrega;

	public Pedido(int idProduto, String descricao, double vlCusto, double vlVenda, String categoria, int idPedidoItem,
			double vlUnitario, int qtProduto, double vlDesconto, int idPedido, Date dtEmissao, Date deEntrega,
			double valorTotal, String observacao) {
		super(idProduto, descricao, vlCusto, vlVenda, categoria, idPedidoItem, vlUnitario, qtProduto, vlDesconto);
		this.idPedido = idPedido;
		this.dtEmissao = dtEmissao;
		this.deEntrega = deEntrega;
		this.valorTotal = valorTotal;
		this.observacao = observacao;
	}

	private double valorTotal;
	private String observacao;

	public int getidPedido() {
		return idPedido;
	}

	public void setidPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Date getdtEmissao() {
		return dtEmissao;
	}

	public void setdtEmissao(Date dtEmissao) {
		this.dtEmissao = dtEmissao;
	}

	public Date getdeEntrega() {
		return deEntrega;
	}

	public void setdeEntrega(Date deEntrega) {
		this.deEntrega = deEntrega;
	}

	public double getvalorTotal() {
		return valorTotal;
	}

	public void setvalorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public PedidoItens[] getListaItens() {
		// TODO Auto-generated method stub
		return null;
	}

}
