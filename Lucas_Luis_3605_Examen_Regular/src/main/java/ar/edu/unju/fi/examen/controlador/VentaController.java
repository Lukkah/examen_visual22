package ar.edu.unju.fi.examen.controlador;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.examen.modelo.Cliente;
import ar.edu.unju.fi.examen.modelo.Factura;
import ar.edu.unju.fi.examen.modelo.Libro;
import ar.edu.unju.fi.examen.service.IClienteService;
import ar.edu.unju.fi.examen.service.ILibroService;
import ar.edu.unju.fi.examen.service.IVentaService;

@Controller
public class VentaController {
	public final static Log LOGGER = LogFactory.getLog("VentasController");
	
	
	@Autowired
	IVentaService ventaServiceImp;
	
	@Autowired
	IClienteService clienteServiceImp;
	
	@Autowired
	ILibroService libroServiceImp;
	
	List<Factura> ventas = new ArrayList<Factura>();
	
	@Autowired
	Cliente cliente;

	@Autowired
	Libro libro;
	
	@Autowired
	List<Libro> libros;
	
	/**
	 * Método que levanta la página de ventas
	 * @param model
	 * @return
	 */
	@RequestMapping("/ventas")
	public String getVentasPage(Model model) {
		model.addAttribute("libros",new ArrayList<Libro>());
		model.addAttribute("libro", new Libro());
		model.addAttribute("cliente",new Cliente());
	return "VentaLibro";
	}

	
	//--------------------------------------------------------------------------------
	/**
	 * Método para buscar el cliente al que se le agregará la factura de compra con el listado de 
	 * libros
	 * @param cliente
	 * @param model
	 * @return redirección a una página de confirmación con la factura agregada al cliente
	 */
	@PostMapping("getCliente")
	public ModelAndView getCliente(@ModelAttribute("cliente") Cliente cliente) {
		ModelAndView model = new ModelAndView("VentaLibro");
		LOGGER.info("Codigo del cliente "+cliente.getCodigo() + " // apellido del "+cliente.getApellido());
		LOGGER.info("Codigo del cliente "+clienteServiceImp.getClienteByCodigo(cliente.getCodigo()).getCodigo() + " // apellido del "+clienteServiceImp.getClienteByCodigo(cliente.getCodigo()).getApellido());
		
		
		this.cliente = clienteServiceImp.getClienteByCodigo(cliente.getCodigo());
		//Guardar la factura en la base de datos con el listado de libros y el cliente asignado
		//model.addObject("libros", factura.getLibros());
		model.addObject("cliente",clienteServiceImp.getClienteByCodigo(cliente.getCodigo()));
		model.addObject("libro",new Libro());
		return model;
	}
	
	/**
	 * Método que busca un libro en la base de datos
	 * para agregar los libros seleccionados en una lista de libros que se asignarán 
	 * posteriormente a una factura
	 * @param libro
	 * @param model
	 * @return listado de libros a la página de ventas
	 */
	@PostMapping("getLibro")
	public ModelAndView addLibroToList(@ModelAttribute("libro") Libro libro) {
		ModelAndView model = new ModelAndView("VentaLibro");
		model.addObject(cliente);
		
		this.libro = libroServiceImp.getLibroByCodigo(libro.getCodigo());
		
		LOGGER.warn("Libro obtenido "+libroServiceImp.getLibroByCodigo(libro.getCodigo()).getCodigo() + " autor: "+ libroServiceImp.getLibroByCodigo(libro.getCodigo()).getAutor());
		libros.add(libroServiceImp.getLibroByCodigo(libro.getCodigo()));
		
		model.addObject("libros",libros);
		
		/**
		 * Lista de libros auxiliar, guarda los libros que estarán en una factura
		 * debería limpiarse cuando se realice la venta
		 */
		
		/**
		 * Factura auxiliar que se agregará a la lista de facturas del cliente
		 * debería limpiarse cuando se realice la venta
		 */
		//factura.setLibros(librosBD);
		
		/**
		 * Sólo se envía la factura ya que esta contiene también el listado de libros
		 */
		//Obtener Libro de la base de datos y guardar en una lista
		//ERROR no debí crear lista de libros para guardar los libros, debí crear una factura, y en su
		//lista debí guardar los libros, luego esa factura se debe agregar (Add) a la lista de facturas
		//de un cliente
		
		return model;
	}
	
	
	/**
	 * Método que registra la factura de la venta y muestra los datos de la misma
	 * @param model
	 * @return
	 */
	@PostMapping("confirmarVenta")
	public String getConfirmarVentaPage(Model model) {
		model.addAttribute("libros",libros);
		model.addAttribute("libro", new Libro());
		model.addAttribute("cliente",cliente);
		ventaServiceImp.registrarVenta(libros, cliente);
		return "ConfirmarVenta";
	}
	
	
	
	
	//Listado Ventas-----------------------------------------------------------------------
	
	
	/**
	 * Método que levanta la página de listado de ventas
	 * @param model
	 * @return
	 */
	
	
	@GetMapping("/listado")
	public String getListadoVentasPage(Model model) {
		model.addAttribute("cliente",cliente);
		model.addAttribute("factura",new Factura());
		return "ListadoVentas";
	}
	
	
	@PostMapping("/getClienteLista")
	public ModelAndView getCliente2(@ModelAttribute("cliente") Cliente cliente2) {
		/**
		 * Debo buscar al cliente en la base de datos usando elk código del cliente que
		 *  traigo como parámetro.
		 *  Guardar el listado de ventas en el model o el cliente con las ventas cargadas.
		 */
	
		ModelAndView model = new ModelAndView("ListadoVentas");
		cliente = clienteServiceImp.getClienteByCodigo(cliente2.getCodigo());
		model.addObject("cliente2",clienteServiceImp.getClienteByCodigo(cliente2.getCodigo()));
		model.addObject("factura",new Factura());
		
		LOGGER.info("cliente elegido: "+ cliente.getApellido());
		model.addObject("ventasTotales",cliente.getCompras());
		return model;
	}
	
	
	@PostMapping("getFactura")
	public ModelAndView getLibrosVendidos(@ModelAttribute("factura") Factura factura) {
		ModelAndView model = new ModelAndView("ListadoVentas");
		
		LOGGER.info("Numero de factura entrante: " + factura.getCodigo());
		LOGGER.info("Codigo de cliente entrante: " + cliente.getCodigo());

		LOGGER.info("Apellido de cliente entrante: " + cliente.getApellido());
		
		LOGGER.info("*************** Datos de factura *****************");
		LOGGER.info("Fecha de factura "+ clienteServiceImp.getFacturaByCodigo(factura.getCodigo(),cliente).getFechaCompra());
		LOGGER.info("Numero de factura encontrada*************: " + clienteServiceImp.getFacturaByCodigo(factura.getCodigo(),cliente).getCodigo());
		LOGGER.info("Cantidad de libros comprados: "+clienteServiceImp.getFacturaByCodigo(factura.getCodigo(),cliente).getLibros().size());
		
		//model.addObject("factura",new Factura());
		model.addObject("factura",clienteServiceImp.getFacturaByCodigo(factura.getCodigo(),cliente));

		model.addObject("ventasTotales",cliente.getCompras());
		model.addObject("cliente",cliente);
		return model;
	}
	
	
}
