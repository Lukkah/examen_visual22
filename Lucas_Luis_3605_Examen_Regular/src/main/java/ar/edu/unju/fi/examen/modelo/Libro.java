package ar.edu.unju.fi.examen.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}



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