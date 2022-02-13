package ar.edu.unju.fi.examen.controlador;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ar.edu.unju.fi.examen.service.ILibroService;

@Controller
public class LibroController {
	private final static Log LOGGER = LogFactory.getLog("LibroController");
	
	
	@Autowired
	ILibroService libroServiceImp;
	
	@GetMapping("/libros")
	public String getListadoLibrosPage(Model model) {
		LOGGER.info("CONTROLLER: LibroController w /libros GET METHOD");
		LOGGER.info("METHOD: getListadoLibrosPage()");
		LOGGER.info("RESULT: Visualiza la página de listado de libros");
		model.addAttribute("libros",libroServiceImp.getLibrosList());
		return "ListadoLibros";
	}
}
