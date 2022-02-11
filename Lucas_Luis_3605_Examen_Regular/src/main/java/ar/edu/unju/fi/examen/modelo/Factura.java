package ar.edu.unju.fi.examen.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Factura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable =false)
	private int codigo;
	
	@OneToMany(mappedBy = "venta")
	private List<Libro> libros = new ArrayList<Libro>();
	
	@Autowired
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENTE_ID")
	private Cliente comprador;
	
	@Column(name = "FECHA_COMPRA")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaCompra;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	public Factura() {
		// TODO Auto-generated constructor stub
	}

	

	public Factura(int codigo, List<Libro> libros, Cliente comprador, LocalDate fechaCompra) {
		super();
		this.codigo = codigo;
		this.libros = libros;
		this.comprador = comprador;
		this.fechaCompra = fechaCompra;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public Cliente getCliente() {
		return comprador;
	}

	public void setCliente(Cliente cliente) {
		this.comprador = cliente;
	}

	public LocalDate getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	public Cliente getComprador() {
		return comprador;
	}

	public void setComprador(Cliente comprador) {
		this.comprador = comprador;
	}
	
	
}
