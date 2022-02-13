package ar.edu.unju.fi.examen.service;

import java.util.List;

import ar.edu.unju.fi.examen.modelo.Cliente;
import ar.edu.unju.fi.examen.modelo.Factura;

public interface IClienteService {
	
	public List<Cliente> getClientesList();
	
	public Cliente getClienteByCodigo(int codigo);
	
	public Factura getFacturaByCodigo(int codigo, Cliente cliente);
	
	/**
	 * Genera una lista de clientes, para probar sin la conexión a la base 
	 * de datos
	 * @param factura
	 * @param cliente
	 */
	//public void generateClientesList();
	
	//public void addFacturaToClient(Factura factura, Cliente cliente);

	
}
