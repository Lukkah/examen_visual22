package ar.edu.unju.fi.examen.controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ar.edu.unju.fi.examen.modelo.Libro;

@Controller
public class LibroController {
	
	List<Libro> libros = new ArrayList<Libro>();
	
	Libro libro1 = new Libro();
	
	Libro libro2 = new Libro();
	
	
	@GetMapping("/libros")
	public String getListadoLibrosPage(Model model) {
		libro1.setAutor("HP");
		libro1.setCodigo(1);
		libro1.setNombre("Dagoon");
		libro1.setFechaPublicacion(LocalDate.now());
		
		libros.add(libro1);
		
		libro2.setAutor("LoveCraft");
		libro2.setCodigo(2);
		libro2.setNombre("Call of Cthulhu");
		libro2.setFechaPublicacion(LocalDate.now());
		
		libros.add(libro2);
		
		model.addAttribute("libros",libros);
		return "ListadoLibros";
	}
}
