package ar.edu.unju.fi.examen.modelo;

import java.time.LocalDate;
import java.util.List;


public class Libro {
	
	
	private int codigo;
	private String nombre;
	private String autor;
	private LocalDate fechaPublicacion;
	private List<Factura> ventas;
	
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}



	public Libro() {
		super();
	}



	public Libro(int codigo, String nombre, String autor, LocalDate fechaPublicacion, List<Factura> ventas) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.autor = autor;
		this.fechaPublicacion = fechaPublicacion;
		this.ventas = ventas;
	}



	public int getCodigo() {
		return codigo;
	}



	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getAutor() {
		return autor;
	}



	public void setAutor(String autor) {
		this.autor = autor;
	}



	public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
	}



	public void setFechaPublicacion(LocalDate fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}



	public List<Factura> getVentas() {
		return ventas;
	}



	public void setVentas(List<Factura> ventas) {
		this.ventas = ventas;
	}
	
	
	
}
