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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "libros_factura", 
	joinColumns = @JoinColumn(name = "FACTURA_ID"),
	inverseJoinColumns = @JoinColumn(name = "LIBROS_ID"))
	private List<Libro> libros = new ArrayList<Libro>();
	
	@Autowired
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENTE_ID")
	private Cliente cliente;
	
	
	@Column(name = "FECHA_COMPRA")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaCompra;
	
	@Column
	private double precioTotal;
	
	//ToString-----------------------------------------------------------------------
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	//Constructores------------------------------------------------------------------
	
	public Factura() {
	}

	

	
	
	/**
	 * @param codigo
	 * @param libros
	 * @param cliente
	 * @param fechaCompra
	 * @param precioTotal
	 */
	public Factura(int codigo, List<Libro> libros, Cliente cliente, LocalDate fechaCompra, double precioTotal) {
		super();
		this.codigo = codigo;
		this.libros = libros;
		this.cliente = cliente;
		this.fechaCompra = fechaCompra;
		this.precioTotal = precioTotal;
	}

	//Métodos accesores---------------------------------------------------------------
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	/**
	 * @return the precioTotal
	 */
	public double getPrecioTotal() {
		return precioTotal;
	}

	/**
	 * @param precioTotal the precioTotal to set
	 */
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	
	
	
}