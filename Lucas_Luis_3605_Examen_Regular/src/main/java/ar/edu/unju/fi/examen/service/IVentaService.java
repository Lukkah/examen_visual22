package ar.edu.unju.fi.examen.service;

import java.util.List;

import ar.edu.unju.fi.examen.modelo.Cliente;
import ar.edu.unju.fi.examen.modelo.Factura;
import ar.edu.unju.fi.examen.modelo.Libro;

public interface IVentaService {
	
	//????????????
	public List<Factura> getFacturaList();
	public void generateFacturaList();
	public void addLibroToFactura(Libro libro);
	//????????????
	
	public void registrarVenta(List<Libro>libros, Cliente cliente);
}
