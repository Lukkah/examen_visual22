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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;
@Component
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable =false)
	private int codigo;
	
	
	@NotBlank(message = "El campo no debe estar vacío")
	@Column
	private String apellido;
	
	@NotBlank(message = "El campo no debe estar vacío")
	@Size(min = 3, message = "Debe ingresar como mínimo 3 caracteres y como máximo 120")
	@Column
	private String nombre;
	

	@NotBlank(message = "El campo no debe estar vacío")
	@Size(min = 3,  message = "Debe ingresar como mínimo 3 caracteres y como máximo 16")
	@Column
	private String usuario;

	@NotBlank(message = "El campo no debe estar vacío")
	@Size(min = 3, max = 120, message = "La contraseña debe tener entre 3 y 20 caracteres")
	@Column
	private String password;


	@NotBlank(message = "El campo no debe estar vacío")
	@Column
	private String estado;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Factura> compras = new ArrayList<Factura>();
	
	//ToString---------------------------------------------------------------------------
	@Override
	public String toString() {
		return super.toString();
	}
	
	//Constructores----------------------------------------------------------------------
	public Cliente() {
	}
	

	
	/**
	 * @param codigo
	 * @param apellido
	 * @param nombre
	 * @param usuario
	 * @param password
	 * @param estado
	 * @param compras
	 */
	public Cliente(int codigo, String apellido, String nombre, String usuario, String password, String estado,
			List<Factura> compras) {
		super();
		this.codigo = codigo;
		this.apellido = apellido;
		this.nombre = nombre;
		this.usuario = usuario;
		this.password = password;
		this.estado = estado;
		this.compras = compras;
	}


	//Métodos accesores-------------------------------------------------------------------
	
	
	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the compras
	 */
	public List<Factura> getCompras() {
		return compras;
	}

	/**
	 * @param compras the compras to set
	 */
	public void setCompras(List<Factura> compras) {
		this.compras = compras;
	}

	
	
}
