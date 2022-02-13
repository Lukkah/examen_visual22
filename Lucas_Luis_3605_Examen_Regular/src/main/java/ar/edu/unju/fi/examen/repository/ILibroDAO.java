package ar.edu.unju.fi.examen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.edu.unju.fi.examen.modelo.Libro;

public interface ILibroDAO extends JpaRepository<Libro, Long>{
	
	/**
	 * Método que permite obtener un listado de libros
	 * ordenados según el número de código
	 * @return listado de libros
	 */
	@Query("from Libro l order by l.codigo")
	public List<Libro> getLibros();
	
	/**
	 * Método que permite obtener un libro de la base de datos según su número de código
	 * @param codigo
	 * @return un libro
	 */
	public Libro findByCodigo(int codigo);
}
