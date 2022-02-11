package ar.edu.unju.fi.examen.modelo;

import java.util.List;

public class Cliente {
	
	private int codigo;
	private String apellido;
	private String nombre;
	private List<Factura> compras;
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public Cliente(int codigo, String apellido, String nombre, List<Factura> compras) {
		super();
		this.codigo = codigo;
		this.apellido = apellido;
		this.nombre = nombre;
		this.compras = compras;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Factura> getCompras() {
		return compras;
	}

	public void setCompras(List<Factura> compras) {
		this.compras = compras;
	}
	
	
}
