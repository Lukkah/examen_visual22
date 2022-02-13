package ar.edu.unju.fi.examen.service.imp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

	public final static Log LOGGER = LogFactory.getLog("VentaServiceImp");
	
	@Autowired
	IVentaDAO ventaDaoImp;
	
	
	@Autowired
	ILibroService libroServiceImp;
	
	@Autowired
	ILibroDAO libroDaoImp;
	
	@Autowired
	Factura factura;
	
	@Override
	public List<Factura> getFacturaList() {
		return ventaDaoImp.getFacturas();
	}

	
	@Override
	public void registrarVenta(List<Libro> libros, Cliente cliente) {
				List<Libro> librosAux = new ArrayList<Libro>();
				factura = new Factura();
				factura.setCliente(cliente);
				//factura.setLibro(libro);
				for (Libro libro : libros) {
					librosAux.add(libroDaoImp.findByCodigo(libro.getCodigo()));
				}

				//factura.setLibros(libroDaoImp.findAll());

				factura.setLibros(librosAux);
				factura.setFechaCompra(LocalDate.now());
				ventaDaoImp.save(factura);
				LOGGER.info("**********VENTA REGISTRADA*****************");
				//LOGGER.info("LIBRO "+ factura.getLibro().getNombre()+" CLIENTE "+ factura.getCliente().getApellido());
			
		
	}




	@Override
	public void addLibroToFactura(Libro libro) {
		// TODO Auto-generated method stub

	}
	@Override
	public void generateFacturaList() {
		// TODO Auto-generated method stub
		
	}

}
