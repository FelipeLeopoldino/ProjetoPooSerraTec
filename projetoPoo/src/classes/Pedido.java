package classes;

import java.util.Date;

public class Pedido {

	private int idpedido;
	private Date dtemissao;
	private Date dtentrega;
	private double valortotal;
	private String observacao;

	public Pedido(int idpedido, Date dtemissao, Date dtentrega, double valortotal, String observacao) {
		this.idpedido = idpedido;
		this.dtemissao = dtemissao;
		this.dtentrega = dtentrega;
		this.valortotal = valortotal;
		this.observacao = observacao;
	}

	public int getIdpedido() {
		return idpedido;
	}

	public void setIdpedido(int idpedido) {
		this.idpedido = idpedido;
	}

	public Date getDtemissao() {
		return dtemissao;
	}

	public void setDtemissao(Date dtemissao) {
		this.dtemissao = dtemissao;
	}

	public Date getDtentrega() {
		return dtentrega;
	}

	public void setDtentrega(Date dtentrega) {
		this.dtentrega = dtentrega;
	}

	public double getValortotal() {
		return valortotal;
	}

	public void setValortotal(double valortotal) {
		this.valortotal = valortotal;
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
