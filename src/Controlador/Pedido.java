package Controlador;

public class Pedido {
	private int codPlato,cantidad;
	private Cliente clt;
	public int getCodPlato() {
		return codPlato;
	}
	public void setCodPlato(int codPlato) {
		this.codPlato = codPlato;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Cliente getClt() {
		return clt;
	}
	public void setClt(Cliente clt) {
		this.clt = clt;
	}
	
}
