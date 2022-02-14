package ar.edu.unju.fi.examen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.edu.unju.fi.examen.modelo.Cliente;

public interface IClienteDAO extends JpaRepository<Cliente, Long>{

	/**
	 * Método que permite obtener un listado de clientes desde la base de datos
	 * Ordenados según el número de código
	 * @return listado de clientes
	 */
	@Query("from Cliente c order by c.codigo")
	public List<Cliente> getClientes();
	
	/**
	 * Método que permite encontrar un cliente según su número de código(id)
	 * @param codigo
	 * @return un cliente
	 */
	public Cliente findByCodigo(int codigo);

	/**
	 * Obtiene un cliente en base al nombre de usuario
	 * usado para el login
	 * @param usuario
	 * @return objeto de tipo cliente
	 */
	public Cliente findByUsuario(String usuario);

}
