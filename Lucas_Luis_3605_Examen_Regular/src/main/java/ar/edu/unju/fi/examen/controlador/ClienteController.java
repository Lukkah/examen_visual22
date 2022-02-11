package ar.edu.unju.fi.examen.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ar.edu.unju.fi.examen.modelo.Cliente;

@Controller
public class ClienteController {
	
	Cliente cliente1 = new Cliente();

	Cliente cliente2 = new Cliente();
	
	List<Cliente> clientes = new ArrayList<Cliente>();
	
	@GetMapping("/clientes")
	public String getListadoClientesPage(Model model) {
		cliente1.setCodigo(1);
		cliente1.setApellido("Lucas");
		cliente1.setNombre("Luis");
		cliente1.setUsuario("Daren");
		
		clientes.add(cliente1);
		
		cliente2.setCodigo(2);
		cliente2.setApellido("Cruz");
		cliente2.setNombre("Ruth");
		cliente2.setUsuario("Jack");
		clientes.add(cliente2);
		
		model.addAttribute("clientes",clientes);
		
		return "ListadoClientes";
	}
}
