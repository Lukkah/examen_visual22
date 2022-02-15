package ar.edu.unju.fi.examen.service.imp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.examen.modelo.Cliente;
import ar.edu.unju.fi.examen.modelo.Factura;
import ar.edu.unju.fi.examen.modelo.Libro;
import ar.edu.unju.fi.examen.repository.ILibroDAO;
import ar.edu.unju.fi.examen.repository.IVentaDAO;
import ar.edu.unju.fi.examen.service.ILibroService;
import ar.edu.unju.fi.examen.service.IVentaService;


@Service
public class VentaServiceImp implements IVentaService {
	
	@Autowired
	IVentaDAO ventaDaoImp;
	
	
	@Autowired
	ILibroService libroServiceImp;
	
	@Autowired
	ILibroDAO libroDaoImp;
	
	@Autowired
	Factura factura;
	
	/**
	 * Método que recupera el listado de facturas de la base de datos
	 * se usa????
	 */
	@Override
	public List<Factura> getFacturaList() {
		return ventaDaoImp.getFacturas();
	}

	/**
	 * Método que permite regustrar una venta en la base de datos
	 * 
	 */
	@Override
	public void registrarVenta(List<Libro> libros, Cliente cliente) {
		double precioTotal = 0;
		
		//Lista de libros auxiliar que guardará los libros traidos de la base de datos
		List<Libro> librosAux = new ArrayList<Libro>();
		
		//Creo la factura de la venta que se guardará
		factura = new Factura();
		
		//Registro el cliente y los libros en la factura
		factura.setCliente(cliente);
		for (Libro libro : libros) {
			//Debo traer los libros de la base de datos, ya que no puedo guardar la factura con
			//una lista creada de forma auxiliar
			librosAux.add(libroDaoImp.findByCodigo(libro.getCodigo()));
			precioTotal = precioTotal + libroDaoImp.findByCodigo(libro.getCodigo()).getPrecio();
		}
		
		//Registro los libros comprados en la factura
		factura.setLibros(librosAux);
		//Registro la fecha de compra
		factura.setFechaCompra(LocalDate.now());
		//Registro la suma de los precios de los libros en la factura
		factura.setPrecioTotal(precioTotal);
		
		//Guardo la factura en la base de datos
		ventaDaoImp.save(factura);
	}

}
