package ar.edu.unju.fi.examen.service;

import java.util.List;

import ar.edu.unju.fi.examen.modelo.Libro;

public interface ILibroService {

	public List<Libro> getLibrosList();
	
	public Libro getLibroByCodigo(int codigo);
	
	//public void generateLibrosList();
	
}
