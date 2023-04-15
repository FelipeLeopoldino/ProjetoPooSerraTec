package classes;

import java.util.ArrayList;
import java.util.Date;

public class CadastroPedidos {

	private ArrayList<Pedido> listaPedidos;

	public CadastroPedidos() {
		this.listaPedidos = new ArrayList<>();
	}

	public void incluirPedido(Pedido pedido) {
		listaPedidos.add(pedido);
	}

	public void alterarPedido(int idpedido, Date dtemissao, Date dtentrega, double valortotal, String observacao) {
		Pedido pedido = buscarPedidoPorId(idpedido);
		if (pedido != null) {
			pedido.setDtemissao(dtemissao);
			pedido.setDtentrega(dtentrega);
			pedido.setValortotal(valortotal);
			pedido.setObservacao(observacao);
		}
	}

	public void excluirPedido(int idpedido) {
		Pedido pedido = buscarPedidoPorId(idpedido);
		if (pedido != null) {
			listaPedidos.remove(pedido);
		}
	}

	public void imprimirPedidoSemProdutos(int idpedido) {
		Pedido pedido = buscarPedidoPorId(idpedido);
		if (pedido != null) {
			System.out.println("Pedido #" + pedido.getIdpedido());
			System.out.println("Data de emissão: " + pedido.getDtemissao());
			System.out.println("Data de entrega: " + pedido.getDtentrega());
			System.out.println("Valor total: " + pedido.getValortotal());
			System.out.println("Observação: " + pedido.getObservacao());
			System.out.println("Cliente: " + pedido.getCliente().getNome());
		}
	}

	public void imprimirPedidoComProdutos(int idpedido) {
		Pedido pedido = buscarPedidoPorId(idpedido);
		if (pedido != null) {
			System.out.println("Pedido #" + pedido.getIdpedido());
			System.out.println("Data de emissão: " + pedido.getDtemissao());
			System.out.println("Data de entrega: " + pedido.getDtentrega());
			System.out.println("Valor total: " + pedido.getValortotal());
			System.out.println("Observação: " + pedido.getObservacao());
			System.out.println("Cliente: " + pedido.getCliente().getNome());
			System.out.println("Produtos: ");
			for (PedidoItens item : pedido.getListaItens()) {
				System.out.println("- " + item.getProduto().getDescricao() + " x " + item.getQtproduto());
			}
		}
	}

	public ArrayList<Pedido> buscarPedidosPorCliente(String nomeCliente) {
		ArrayList<Pedido> pedidosEncontrados = new ArrayList<>();
		for (Pedido pedido : listaPedidos) {
			if (pedido.getCliente().getNome().equalsIgnoreCase(nomeCliente)) {
				pedidosEncontrados.add(pedido);
			}
		}
		return pedidosEncontrados;
	}

	public ArrayList<Pedido> buscarPedidosPorData(Date dataInicio, Date dataFim) {
		ArrayList<Pedido> pedidosEncontrados = new ArrayList<>();
		for (Pedido pedido : listaPedidos) {
			if (pedido.getDtemissao().after(dataInicio) && pedido.getDtemissao().before(dataFim)) {
				pedidosEncontrados.add(pedido);
			}
		}
		return pedidosEncontrados;
	}

	public Pedido buscarPedidoPorId(int idpedido) {
		for (Pedido pedido : listaPedidos) {
			if (pedido.getIdpedido() == idpedido) {
				return pedido;
			}
		}
		return null;
	}
}