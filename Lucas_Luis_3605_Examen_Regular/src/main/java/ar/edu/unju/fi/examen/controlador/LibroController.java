package ar.edu.unju.fi.examen.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ar.edu.unju.fi.examen.service.ILibroService;

@Controller
public class LibroController {
	
	@Autowired
	ILibroService libroServiceImp;
	
	@GetMapping("/libros")
	public String getListadoLibrosPage(Model model) {
		model.addAttribute("libros",libroServiceImp.getLibrosList());
		return "ListadoLibros";
	}
}
