package ar.edu.unju.fi.examen.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable =false)
	private int codigo;
	
	@Column
	private String nombre;
	
	@Column
	private String autor;
	
	@Column(name = "FECHA_PUBLICACION")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaPublicacion;
	
	//ToString------------------------------------------------------------------------
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}


	//Constructores-------------------------------------------------------------------
	
	public Libro() {
		super();
	}
	
	public Libro(int codigo, String nombre, String autor, LocalDate fechaPublicacion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.autor = autor;
		this.fechaPublicacion = fechaPublicacion;
	}

	//Métodos accesores----------------------------------------------------------------

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



	
	
}