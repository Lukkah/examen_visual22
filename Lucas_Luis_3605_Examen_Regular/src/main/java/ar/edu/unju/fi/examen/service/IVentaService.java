package ar.edu.unju.fi.examen.service;

import java.util.List;

import ar.edu.unju.fi.examen.modelo.Cliente;
import ar.edu.unju.fi.examen.modelo.Factura;
import ar.edu.unju.fi.examen.modelo.Libro;

public interface IVentaService {
	
	public void registrarVenta(List<Libro>libros, Cliente cliente);
	
	public List<Factura> getFacturaList();

	
}
