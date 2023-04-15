package classes;

import java.util.ArrayList;

public interface CRUD {

	public void cadastroPedido();

	public void incluirPedidos();

	public void alterarPedidos();

	public void exluirPedidos();

	public void imprimirPedidoSemProdutos();

	public void imprimirPedidoComProdutos();

	public ArrayList<Pedido> buscarPedidosPorCliente();

	public ArrayList<Pedido> buscarPedidosPorData();

	public Pedido buscarPedidoPorId();

}
