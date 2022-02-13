package ar.edu.unju.fi.examen.controlador;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.examen.modelo.Cliente;
import ar.edu.unju.fi.examen.service.IClienteService;

@Controller
public class ClienteController {
	public final static Log LOGGER = LogFactory.getLog("ClienteController");
	
	/*
	Cliente cliente1 = new Cliente();

	Cliente cliente2 = new Cliente();
	
	List<Cliente> clientes = new ArrayList<Cliente>();
	
	*/
	
	@Autowired
	IClienteService clienteServiceImp;
	
	@Autowired
	Cliente cliente;
	
	@GetMapping("/clientes")
	public String getListadoClientesPage(Model model) {
		
		clienteServiceImp.generateClientesList();
		model.addAttribute("clientes",clienteServiceImp.getClientesList());
		model.addAttribute("cliente",cliente);
		model.addAttribute("cliente2",cliente);
		
		LOGGER.info("Cantidad de compras del cliente encontrado " +clienteServiceImp.getClientesList().get(0).getCompras().size());
		return "ListadoClientes";
	}
	
	@PostMapping("/getCliente2")
	public ModelAndView getCliente2(@ModelAttribute("cliente") Cliente cliente2) {
		/**
		 * Debo buscar al cliente en la base de datos usando elk código del cliente que
		 *  traigo como parámetro.
		 *  Guardar el listado de ventas en el model o el cliente con las ventas cargadas.
		 */
		ModelAndView model = new ModelAndView("ListadoClientes");
		model.addObject("clientes",clienteServiceImp.getClientesList());
		model.addObject("cliente2",clienteServiceImp.getClienteByCodigo(cliente2.getCodigo()));
		return model;
	}
}
