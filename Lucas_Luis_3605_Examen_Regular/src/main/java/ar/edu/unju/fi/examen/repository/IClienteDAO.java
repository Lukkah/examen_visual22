package ar.edu.unju.fi.examen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.edu.unju.fi.examen.modelo.Cliente;

public interface IClienteDAO extends JpaRepository<Cliente, Long>{


	@Query("from Cliente c order by c.codigo")
	public List<Cliente> getClientes();
	
	
	public Cliente findByCodigo(int codigo);
}
