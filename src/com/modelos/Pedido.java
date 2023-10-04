package com.modelos;

public class Pedido {

	private String id;
	private String fechaPedido;
	private double total;
	private String cedulaCliente;

	public Pedido(String id, String fechaPedido, double total, String cedulaCliente) {
		this.id = id;
		this.fechaPedido = fechaPedido;
		this.total = total;
		this.cedulaCliente = cedulaCliente;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(String fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getCedulaCliente() {
		return cedulaCliente;
	}

	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}

}
