package ar.edu.unju.fi.examen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.edu.unju.fi.examen.modelo.Libro;

public interface ILibroDAO extends JpaRepository<Libro, Long>{
	
	@Query("from Libro l order by l.codigo")
	public List<Libro> getLibros();
	
	
	public Libro findByCodigo(int codigo);
}
