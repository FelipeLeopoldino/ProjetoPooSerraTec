package classes;

import java.util.Date;

public class Pedido extends PedidoItens {

	private int idPedido;
	private Date dtEmissao;
	private Date dtEntrega;
	private double valorTotal;
	private String observacao;

	public Pedido(int idProduto, String descricao, double vlCusto, double vlVenda, String categoria, int idPedidoItem,
			double vlUnitario, int qtProduto, double vlDesconto, int idPedido, Date dtEmissao, Date dtEntrega,
			double valorTotal, String observacao) {
		super(idProduto, descricao, vlCusto, vlVenda, categoria, idPedidoItem, vlUnitario, qtProduto, vlDesconto);
		this.idPedido = idPedido;
		this.dtEmissao = dtEmissao;
		this.dtEntrega = dtEntrega;
		this.valorTotal = valorTotal;
		this.observacao = observacao;
	}

	public boolean incluirCliente(Cliente cliente) {
		boolean temCliente = true;

		if (!temCliente) {
			cliente.setidCliente(idPedido);
			System.out.println("Cliente inserido com sucesso!!");
		} else {
			cliente.getidCliente();
		}

		return temCliente;
	}
	
	

	public PedidoItens[] getListaItens() {
		return null;
	}

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

	public Date getdtEntrega() {
		return dtEntrega;
	}

	public void setdtEntrega(Date dtEntrega) {
		this.dtEntrega = dtEntrega;
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

}
