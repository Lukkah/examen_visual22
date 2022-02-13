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
	private final static Log LOGGER = LogFactory.getLog("VentasController");
	
	
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
	
	
	//MÉTODOS PARA LA COMPRA DE LIBROS---------------------------------------------------------------------------------
	/**
	 * Método que levanta la página de ventas
	 * @param model
	 * @return
	 */
	@RequestMapping("/ventas")
	public String getVentasPage(Model model) {
		LOGGER.info("CONTROLLER: VentaController w /getVentasPage GET METHOD");
		LOGGER.info("METHOD: getVentasPage()");
		LOGGER.info("RESULT: Visualiza la página de venta de un libro");
		
		//Limpio la lista de libros
		libros = new ArrayList<Libro>();
		
		model.addAttribute("libros",new ArrayList<Libro>());
		model.addAttribute("libro", new Libro());
		model.addAttribute("cliente",new Cliente());
	return "VentaLibro";
	}

	/**
	 * Método para buscar el cliente al que se le agregará la factura de compra con el listado de 
	 * libros
	 * 
	 * *************************************************
	 * Este método no debe existir!!!!!!!!
	 * 
	 * Debo tener los datos del cliente ya cargados, no debo seleccionarlos manualmente
	 * Deben venir desde que se inicia sesión
	 * 
	 * ************************************************
	 *
	 * @param cliente
	 * @param model
	 * @return redirección a una página de confirmación con la factura agregada al cliente
	 */
	@PostMapping("getCliente")
	public ModelAndView getCliente(@ModelAttribute("cliente") Cliente cliente) {
		ModelAndView model = new ModelAndView("VentaLibro");
		LOGGER.info("CONTROLLER: VentaController w /getCliente POST METHOD");
		LOGGER.info("METHOD: getCliente()");
		LOGGER.info("RESULT: Obtiene un cliente de la base de datos según el código");
		
		LOGGER.info("Codigo del cliente(parámetro) "+cliente.getCodigo() + 
					" // apellido del cliente(parámetro) "+cliente.getApellido());
		
		LOGGER.info("Codigo del cliente encontrado: "+
					clienteServiceImp.getClienteByCodigo(cliente.getCodigo()).getCodigo()+ 
					"// apellido del cliente encontrado: "+
					clienteServiceImp.getClienteByCodigo(cliente.getCodigo()).getApellido());
		

		this.cliente = clienteServiceImp.getClienteByCodigo(cliente.getCodigo());
		model.addObject("cliente",clienteServiceImp.getClienteByCodigo(cliente.getCodigo()));
		model.addObject("libro",new Libro());
		return model;
	}
	
	/**
	 * Método que busca un libro en la base de datos
	 * para agregar los seleccionados en una lista que se asignarán 
	 * posteriormente a una factura
	 * @param libro
	 * @param model
	 * @return listado de libros a la página de ventas
	 */
	@PostMapping("getLibro")
	public ModelAndView addLibroToList(@ModelAttribute("libro") Libro libro) {
		LOGGER.info("CONTROLLER: VentaController w /getLibro POST METHOD");
		LOGGER.info("METHOD: addLibroToList()");
		LOGGER.info("RESULT: Obtiene un cliente de la base de datos según el código");
		/**
		 * Sirve para levantar una página desde un método con objetos asignados
		 * VentaLibro es la página
		 */
		ModelAndView model = new ModelAndView("VentaLibro");
		
		//Agrego cliente para que cuando seleccione un libro no se borre de la lista
		model.addObject(cliente);
		
		LOGGER.info(
					"Libro obtenido "+libroServiceImp.getLibroByCodigo(libro.getCodigo()).getCodigo() + 
					" autor: "+ libroServiceImp.getLibroByCodigo(libro.getCodigo()).getAutor());
		
		/**
		 * Lista de libros auxiliar, guarda los libros que estarán en una factura
		 * debería limpiarse cuando se realice la venta
		 */
		libros.add(libroServiceImp.getLibroByCodigo(libro.getCodigo()));
		
		//Con cada libro nuevo se va guardando la lista actualizada
		model.addObject("libros",libros);
			
		return model;
	}
	
	
	/**
	 * Método que registra la factura de la venta y muestra los datos de la misma
	 * @param model
	 * @return
	 */
	@PostMapping("confirmarVenta")
	public String getConfirmarVentaPage(Model model) {
		LOGGER.info("CONTROLLER: VentaController w /getConfirmarVentaPage POST METHOD");
		LOGGER.info("METHOD: getConfirmarVentaPage)");
		LOGGER.info("RESULT: Registra una venta con el cliente y libros asignados en una factura");
		
		
		model.addAttribute("libros",libros);
		model.addAttribute("libro", new Libro());
		model.addAttribute("cliente",cliente);
		
		LOGGER.info("**********DATOS DE LA COMPRA*********");
		LOGGER.info("Apellido del comprador: "+ cliente.getApellido());
		LOGGER.info("Cantidad de libros comprados: "+ libros.size());
		LOGGER.info("Total a pagar: ");
		
		//Método de la capa de servicio que registra una venta
		ventaServiceImp.registrarVenta(libros, cliente);
		
		
		//limpio la lista de libros
		libros = new ArrayList<Libro>();
		
		/**********************************************************************************
		 * 
		 * 
		 * Obtener última venta realizada y agregarla al model para visualizarla
		 * 								   ---
		 * 									*
		 * 									*
		 * 									*
		 * 									V
		 * *************************************************************************
		 */
		model.addAttribute("factura",new Factura());
		
		return "ConfirmarVenta";
	}
	
	
	
	
	//MÉTODOS PARA VER LAS FACTURAS-----------------------------------------------------------------------
	
	
	/**
	 * Método que levanta la página de listado de ventas
	 * @param model
	 * @return
	 */
	@GetMapping("/listado")
	public String getListadoVentasPage(Model model) {
		LOGGER.info("CONTROLLER: VentaController w /listado POST METHOD");
		LOGGER.info("METHOD: getListadoVentasPage()");
		LOGGER.info("RESULT: Obtiene la página de las facturas");
		
		
		/**
		 * Agrego a la vista un objeto de tipo Cliente, debería ser obtenido
		 * previamente de la base de datos
		 * 
		 */
		model.addAttribute("cliente",cliente);
		model.addAttribute("factura",new Factura());
		return "ListadoVentas";
	}
	
	/**
	 * ******************************************************************
	 * !!!!!!!!!!!!!!!Este método no debe existir!!!!!!!!
	 * 
	 * Debo tener los datos del cliente ya cargados, no debo seleccionarlos manualmente
	 * Deben venir desde que se inicia sesión
	 * ******************************************************************
	 * @param cliente2
	 * @return
	 */
	@PostMapping("/getClienteLista")
	public ModelAndView getCliente2(@ModelAttribute("cliente") Cliente cliente2) {
		LOGGER.info("CONTROLLER: VentaController w /getClienteLista POST METHOD");
		LOGGER.info("METHOD: getCliente2()");
		LOGGER.info("RESULT: Obtiene un cliente de la base de datos según el código");
		
		LOGGER.info("Codigo del cliente(parámetro) "+cliente2.getCodigo() + 
					" // apellido del cliente(parámetro) "+cliente2.getApellido());
		
		LOGGER.info("Codigo del cliente encontrado: "+
					clienteServiceImp.getClienteByCodigo(cliente2.getCodigo()).getCodigo()+ 
					"// apellido del cliente encontrado: "+
					clienteServiceImp.getClienteByCodigo(cliente2.getCodigo()).getApellido());
		
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
		LOGGER.info("CONTROLLER: VentaController w /getFactura POST METHOD");
		LOGGER.info("METHOD: getLibrosVendidos()");
		LOGGER.info("RESULT: Obtiene los libros vendidos de una factura determinada");
		
		ModelAndView model = new ModelAndView("ListadoVentas");
		
		LOGGER.info("Numero de factura entrante: " + factura.getCodigo());
		LOGGER.info("Codigo del comprador: " + cliente.getCodigo());
		LOGGER.info("Apellido del comprador: " + cliente.getApellido());
		
		LOGGER.info("*************** Datos de factura *****************");
		LOGGER.info("Fecha de factura "+ clienteServiceImp.getFacturaByCodigo(factura.getCodigo(),cliente).getFechaCompra());
		LOGGER.info("Código de la factura: " + clienteServiceImp.getFacturaByCodigo(factura.getCodigo(),cliente).getCodigo());
		LOGGER.info("Cantidad de libros comprados: "+clienteServiceImp.getFacturaByCodigo(factura.getCodigo(),cliente).getLibros().size());
		
		//Envío la factura que se recuperda desde la base de datos, según el código de factura
		model.addObject("factura",clienteServiceImp.getFacturaByCodigo(factura.getCodigo(),cliente));
		
		//Envío el listado de compras(facturas) del cliente guardado en el objeto auxiliar
		model.addObject("ventasTotales",cliente.getCompras());
		
		//Envío el cliente que se ha recuperado de la base de datos en métodos anteriores
		//y se ha guardado en un objeto auxiliar
		model.addObject("cliente",cliente);
		return model;
	}
	
	
}
