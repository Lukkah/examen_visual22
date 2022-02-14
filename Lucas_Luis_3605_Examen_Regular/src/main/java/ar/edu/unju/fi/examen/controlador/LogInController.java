package ar.edu.unju.fi.examen.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogInController {
	
	@GetMapping("/login")
	public String getLogInPage() {
		return "LogIn";
	}

	@GetMapping("/logout")
	public String getLogOutPage() {
		return "LogIn";
	}
}
