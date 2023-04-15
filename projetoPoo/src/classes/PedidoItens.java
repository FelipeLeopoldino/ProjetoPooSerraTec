package classes;

public class PedidoItens {

	private int idpedidoitem;
    private double vlunitario;
    private int qtproduto;
    private double vldesconto;
    
    public PedidoItens(int idpedidoitem, double vlunitario, int qtproduto, double vldesconto) {
        this.idpedidoitem = idpedidoitem;
        this.vlunitario = vlunitario;
        this.qtproduto = qtproduto;
        this.vldesconto = vldesconto;
    }
    
    public int getIdpedidoitem() {
        return idpedidoitem;
    }
    
    public void setIdpedidoitem(int idpedidoitem) {
        this.idpedidoitem = idpedidoitem;
    }
    
    public double getVlunitario() {
        return vlunitario;
    }
    
    public void setVlunitario(double vlunitario) {
        this.vlunitario = vlunitario;
    }
    
    public int getQtproduto() {
        return qtproduto;
    }
    
    public void setQtproduto(int qtproduto) {
        this.qtproduto = qtproduto;
    }
    
    public double getVldesconto() {
        return vldesconto;
    }
    
    public void setVldesconto(double vldesconto) {
        this.vldesconto = vldesconto;
    }
}
