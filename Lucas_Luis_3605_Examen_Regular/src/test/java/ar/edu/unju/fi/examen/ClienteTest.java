package ar.edu.unju.fi.examen;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ar.edu.unju.fi.examen.modelo.Cliente;
import ar.edu.unju.fi.examen.repository.IClienteDAO;

@SpringBootTest
class ClienteTest {

	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Autowired
	IClienteDAO clienteDaoImp;
	
	@Test
	void testGuardarCliente() {
		/*
		Cliente cliente = new Cliente(4, "Gonzales", "Isaac", "zak", encoder.encode("123"), null);
		clienteDaoImp.save(cliente);
		
		assertEquals(cliente.getApellido(),"Gonzales");
		
		*/
	}

}
