package ar.edu.unju.fi.examen.service;

import java.util.List;

import ar.edu.unju.fi.examen.modelo.Cliente;
import ar.edu.unju.fi.examen.modelo.Factura;

public interface IClienteService {
	
	public List<Cliente> getClientesList();
	
	public Cliente getClienteByCodigo(int codigo);
	
	public void generateClientesList();
	
	public void addFacturaToClient(Factura factura, Cliente cliente);

	public Factura getFacturaByCodigo(int codigo, Cliente cliente);
	
}
