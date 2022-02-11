package ar.edu.unju.fi.examen.modelo;

import java.time.LocalDate;

public class Factura {
	
	private int codigo;
	private Libro libro;
	private Cliente cliente;
	private LocalDate fechaCompra;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	public Factura() {
		// TODO Auto-generated constructor stub
	}

	public Factura(int codigo, Libro libro, Cliente cliente, LocalDate fechaCompra) {
		super();
		this.codigo = codigo;
		this.libro = libro;
		this.cliente = cliente;
		this.fechaCompra = fechaCompra;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	
	
}
