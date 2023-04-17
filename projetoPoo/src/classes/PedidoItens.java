package classes;

public class PedidoItens extends Produto {

	private int idPedidoItem;
	private double vlUnitario;
	private int qtProduto;
	private double vlDesconto;


	public int getidPedidoItem() {
		return idPedidoItem;
	}

	public void setidPedidoItem(int idPedidoItem) {
		this.idPedidoItem = idPedidoItem;
	}

	public double getvlUnitario() {
		return vlUnitario;
	}

	public void setvlUnitario(double vlUnitario) {
		this.vlUnitario = vlUnitario;
	}

	public int getqtProduto() {
		return qtProduto;
	}

	public void setqtProduto(int qtProduto) {
		this.qtProduto = qtProduto;
	}

	public double getvlDesconto() {
		return vlDesconto;
	}

	public void setvlDesconto(double vlDesconto) {
		this.vlDesconto = vlDesconto;
	}

	public void setVlPreco(double double1) {
		// TODO Auto-generated method stub
		
	}


}
