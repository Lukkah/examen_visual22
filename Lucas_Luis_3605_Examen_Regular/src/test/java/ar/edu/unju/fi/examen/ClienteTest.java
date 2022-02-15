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
		
		Cliente cliente = new Cliente();
		
		cliente.setCodigo(1);
		cliente.setApellido("Lucas");
		cliente.setEstado("HABILITADO");
		cliente.setNombre("Luis");
		cliente.setUsuario("daren");
		cliente.setPassword(encoder.encode("123456"));
		clienteDaoImp.save(cliente);
		
		Cliente cliente2 = new Cliente();
		cliente2.setApellido("Gonzales");
		cliente2.setCodigo(2);
		cliente2.setEstado("HABILITADO");
		cliente2.setNombre("Isaac");
		cliente2.setPassword(encoder.encode("123456"));
		cliente2.setUsuario("zak");
		clienteDaoImp.save(cliente2);

		Cliente cliente3 = new Cliente();
		cliente3.setApellido("Mamani");
		cliente3.setCodigo(3);
		cliente3.setEstado("HABILITADO");
		cliente3.setNombre("Alex");
		cliente3.setPassword(encoder.encode("123456"));
		cliente3.setUsuario("esray");

		clienteDaoImp.save(cliente3);
		
		assertEquals(cliente.getApellido(),"Lucas");
		
		
	}

}
