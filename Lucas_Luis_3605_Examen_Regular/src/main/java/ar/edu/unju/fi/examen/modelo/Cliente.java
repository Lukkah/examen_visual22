package ar.edu.unju.fi.examen.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;
@Component
@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable =false)
	private int codigo;
	
	@Column
	private String apellido;
	
	@Column
	private String nombre;
	
	@Column(name="NOMBRE_USUARIO")
	private String usuario;
	
	@Column
	private String password;
	
	@OneToMany(mappedBy = "comprador", cascade = CascadeType.ALL)
	private List<Factura> compras = new ArrayList<Factura>();
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	public Cliente(int codigo, String apellido, String nombre, String usuario, String password, List<Factura> compras) {
		super();
		this.codigo = codigo;
		this.apellido = apellido;
		this.nombre = nombre;
		this.usuario = usuario;
		this.password = password;
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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
