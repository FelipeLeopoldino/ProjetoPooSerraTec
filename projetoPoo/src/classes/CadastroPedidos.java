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

	public void alterarPedido(int idpedido, Date dtemissao, Date dtentrega, double valortotal, Cliente cliente,
			Produto produto) {
		Pedido pedido = buscarPedidoPorId(idpedido);

		if (pedido != null) {
			pedido.setdtEmissao(dtemissao);
			pedido.setdtEntrega(dtentrega);
			pedido.setvalorTotal(valortotal);
			cliente.setIdCliente(idpedido);
			produto.setIdProduto(idpedido);

		}
	}

	public void excluirPedido(int idpedido) {
		Pedido pedido = buscarPedidoPorId(idpedido);
		if (pedido != null) {
			listaPedidos.remove(pedido);
		}
	}

	public void imprimirPedidoSemProdutos(int idpedido, Cliente cliente) {
		Pedido pedido = buscarPedidoPorId(idpedido);
		if (pedido != null) {
			System.out.println("Pedido #" + pedido.getidPedido());
			System.out.println("Data de emissão: " + pedido.getdtEmissao());
			System.out.println("Data de entrega: " + pedido.getdtEntrega());
			System.out.println("Valor total: " + pedido.getvalorTotal());
			System.out.println("Observação: " + pedido.getObservacao());
			System.out.println("Cliente: " + cliente.getNome());
		}
	}

	public void imprimirPedidoComProdutos(int idpedido, Cliente cliente) {
		Pedido pedido = buscarPedidoPorId(idpedido);
		if (pedido != null) {
			System.out.println("Pedido #" + pedido.getidPedido());
			System.out.println("Data de emissão: " + pedido.getdtEmissao());
			System.out.println("Data de entrega: " + pedido.getdtEntrega());
			System.out.println("Valor total: " + pedido.getvalorTotal());
			System.out.println("Observação: " + pedido.getObservacao());
			System.out.println("Cliente: " + cliente.getNome());
			System.out.println("Produtos: ");
			for (PedidoItens item : pedido.getListaItens()) {
				System.out.println("- " + item.getDescricao() + " x " + item.getqtProduto());
			}
		}
	}

	public ArrayList<Pedido> buscarPedidosPorCliente(String nomeCliente, Cliente cliente) {
		ArrayList<Pedido> pedidosEncontrados = new ArrayList<>();
		for (Pedido pedido : listaPedidos) {
			if (cliente.getNome().equalsIgnoreCase(nomeCliente)) {
				pedidosEncontrados.add(pedido);
			}
		}
		return pedidosEncontrados;
	}

	public ArrayList<Pedido> buscarPedidosPorData(Date dataInicio, Date dataFim) {
		ArrayList<Pedido> pedidosEncontrados = new ArrayList<>();
		for (Pedido pedido : listaPedidos) {
			if (pedido.getdtEmissao().after(dataInicio) && pedido.getdtEmissao().before(dataFim)) {
				pedidosEncontrados.add(pedido);
			}
		}
		return pedidosEncontrados;
	}

	public Pedido buscarPedidoPorId(int idpedido) {
		for (Pedido pedido : listaPedidos) {
			if (pedido.getidPedido() == idpedido) {
				return pedido;
			}
		}
		return null;
	}
}