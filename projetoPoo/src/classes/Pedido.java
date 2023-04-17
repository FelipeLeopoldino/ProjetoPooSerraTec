package classes;

import java.util.ArrayList;
import java.util.Date;

public class Pedido extends PedidoItens {
	
	private int idPedido;
	private Date dtEmissao;
	private Date dtEntrega;
	private double valorTotal;
	private String observacao;
	private Cliente cliente = new Cliente();
	private ArrayList<PedidoItens> itens = new ArrayList<>();


	public boolean incluirCliente(Cliente cliente) {
		boolean temCliente = true;

		if (!temCliente) {
			cliente.setIdCliente(idPedido);
			System.out.println("Cliente inserido com sucesso!!");
		} else {
			cliente.getIdCliente();
		}

		return temCliente;
	}
	
	public void adicionarItem(PedidoItens item) {
		PedidoItens pedidoItem = new PedidoItens();
		
		pedidoItem.setDescricao(item.getDescricao());
		pedidoItem.setIdProduto(item.getIdProduto());
		pedidoItem.setqtProduto(item.getqtProduto());
		pedidoItem.setVlCusto(item.getVlCusto());
		pedidoItem.setVlVenda(item.getVlVenda());
		
		
		itens.add(pedidoItem);
				
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
