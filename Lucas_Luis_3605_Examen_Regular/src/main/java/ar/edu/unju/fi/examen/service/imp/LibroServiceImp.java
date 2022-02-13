package ar.edu.unju.fi.examen.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.examen.modelo.Libro;
import ar.edu.unju.fi.examen.repository.ILibroDAO;
import ar.edu.unju.fi.examen.service.ILibroService;


@Service
public class LibroServiceImp implements ILibroService {

	List<Libro> libros = new ArrayList<Libro>();
	
	Libro libro1 = new Libro();
	
	Libro libro2 = new Libro();
	
	
	@Autowired
	ILibroDAO libroDao;
	
	/**
	 * Método que devuelve un listado de libros
	 */
	@Override
	public List<Libro> getLibrosList() {
		return libroDao.getLibros();
	}
	
	
	/**
	 * Método que devuelve un libro según su número de código
	 */
	@Override
	public Libro getLibroByCodigo(int codigo) {
		/*
		Libro libro3 = new Libro();
		for (Libro	libro : libros) {
			if(libro.getCodigo()==codigo){
				libro3 = libro;
			}
		}
		return libro3;
		*/
		return libroDao.findByCodigo(codigo);
	}

	/**
	@Override
	public void generateLibrosList() {
		if(libros.size() == 0) {
			libro1.setAutor("HP");
			libro1.setCodigo(1);
			libro1.setNombre("Dagoon");
			libro1.setFechaPublicacion(LocalDate.now());
			
			libros.add(libro1);
			
			libro2.setAutor("LoveCraft");
			libro2.setCodigo(2);
			libro2.setNombre("Call of Cthulhu");
			libro2.setFechaPublicacion(LocalDate.now());
			
			libros.add(libro2);
		}

	}
	*/
}
