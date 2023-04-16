package classes;

import java.util.ArrayList;
import java.util.Date;

public class Pedido extends PedidoItens {

	private int idPedido;
	private Date dtEmissao;
	private Date dtEntrega;
	private double valorTotal;
	private String observacao;
	private Cliente cliente;
	private ArrayList<PedidoItens> itens;

	public Pedido(int idProduto, String descricao, double vlCusto, double vlVenda, String categoria, int idPedidoItem,
			double vlUnitario, int qtProduto, double vlDesconto, int idPedido, Date dtEmissao, Date dtEntrega,
			double valorTotal, String observacao, Cliente cliente, ArrayList<PedidoItens> itens) {
		super(idProduto, descricao, vlCusto, vlVenda, categoria, idPedidoItem, vlUnitario, qtProduto, vlDesconto);
		this.idPedido = idPedido;
		this.dtEmissao = dtEmissao;
		this.dtEntrega = dtEntrega;
		this.valorTotal = valorTotal;
		this.observacao = observacao;
		this.cliente = cliente;
		this.itens = new ArrayList<>();
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
	
	public void adicionarItem(PedidoItens item) {
		itens.add(item);
	}

	public void removerItem(PedidoItens item) {
		itens.remove(item);
	}

	public void atualizarItem(PedidoItens item, int quantidade, double valorUnitario, double desconto) {
		item.setqtProduto(quantidade);
		item.setvlUnitario(valorUnitario);
		item.setvlDesconto(desconto);
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<PedidoItens> getProdutos() {
		return itens;
	}

	public void setProdutos(ArrayList<PedidoItens> produtos) {
		this.itens = produtos;
	}

}
