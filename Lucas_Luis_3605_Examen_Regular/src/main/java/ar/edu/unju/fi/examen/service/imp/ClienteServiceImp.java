package ar.edu.unju.fi.examen.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.examen.modelo.Cliente;
import ar.edu.unju.fi.examen.modelo.Factura;
import ar.edu.unju.fi.examen.repository.IClienteDAO;
import ar.edu.unju.fi.examen.service.IClienteService;
import ar.edu.unju.fi.examen.service.IVentaService;

@Service
public class ClienteServiceImp implements IClienteService{
	public final static Log LOGGER = LogFactory.getLog("ClienteServiceImp");
	
	
	List<Cliente> clientes = new ArrayList<Cliente>();
	
	Cliente cliente1 = new Cliente();

	Cliente cliente2 = new Cliente();
	
	@Autowired
	IClienteDAO clienteDaoImp;
	
	@Autowired
	IVentaService facturaServiceImp;
	
	
	/**
	 * Método que devuelve un listado de clientes de la base de datos
	 */
	@Override
	public List<Cliente> getClientesList() {
		return clienteDaoImp.getClientes();
	}

	/**
	 * Método que devuelve un cliente según su número de código
	 */
	@Override
	public Cliente getClienteByCodigo(int codigo) {
		
		return clienteDaoImp.findByCodigo(codigo);
	}

	
	
	
	/**
	 * Método que obtiene una factura determinada de un cliente
	 * según el código de la factura recibida por parámetro
	 */
	@Override
	public Factura getFacturaByCodigo(int codigoFactura, Cliente cliente) {
		//Creo una factura auxiliar
		Factura facturaAux = new Factura();
		
		//Recorro el listado de compras del cliente(facturas)
		for (Factura factura : getClienteByCodigo(cliente.getCodigo()).getCompras()) {
			//Almaceno la factura encontrada cuyo código coincide con el enviado por parámetro
			if(factura.getCodigo() == codigoFactura) {
				facturaAux = factura;
			}
		}
		
		//devuelvo la factura encontrada
		return facturaAux;
	}

	
	
}
