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
	
	@Override
	public List<Cliente> getClientesList() {
		return clienteDaoImp.getClientes();
	}

	@Override
	public Cliente getClienteByCodigo(int codigo) {
		
		
		/*
		Cliente cliente3 = new Cliente();
		LOGGER.info("Codigo entrante: "+ codigo);
		for (Cliente cliente : clientes) {

			LOGGER.info("Codigo entrante: "+ codigo + " == Codigo cliente en for: "+ cliente.getCodigo());
			
			if(cliente.getCodigo()==codigo){
				LOGGER.info("Codigo entrante: "+ codigo + " == Codigo cliente encontrado: "+ cliente.getCodigo());
				cliente3 = cliente;
			}
		}
		LOGGER.info("Codigo entrante: "+ codigo + " == Codigo cliente saliente: "+ cliente3.getCodigo());
		return cliente3;
		*/
		
		return clienteDaoImp.findByCodigo(codigo);
	}

	@Override
	public void generateClientesList() {
		if(clientes.size()==0) {
			facturaServiceImp.generateFacturaList();
			cliente1.setCodigo(1);
			cliente1.setApellido("Lucas");
			cliente1.setNombre("Luis");
			cliente1.setUsuario("Daren");
			
			LOGGER.info("Tamaño de factura en cliente service imp: "+ facturaServiceImp.getFacturaList().size());
			cliente1.setCompras(facturaServiceImp.getFacturaList());
			
			clientes.add(cliente1);
			
			cliente2.setCodigo(2);
			cliente2.setApellido("Cruz");
			cliente2.setNombre("Ruth");
			cliente2.setUsuario("Jack");
			clientes.add(cliente2);
			
		}
	}

	@Override
	public void addFacturaToClient(Factura factura, Cliente cliente) {
		List<Factura> facturasAux = new ArrayList<Factura>();
		
		facturasAux = cliente.getCompras();
		facturasAux.add(factura);
		
		for (int i=0;i<clientes.size();i++) {
			if(clientes.get(i).getCodigo() == cliente.getCodigo()) {
				clientes.get(i).setCompras(facturasAux);
			}
		}
	}
	
	@Override
	public Factura getFacturaByCodigo(int codigo, Cliente cliente) {
		Factura facturaAux = new Factura();
		for (Factura factura : getClienteByCodigo(cliente.getCodigo()).getCompras()) {
			if(factura.getCodigo() == codigo) {
				facturaAux = factura;
				//LOGGER.info("---------Factura encontrada-------------");
				//LOGGER.info("codigo: " + facturaAux.getLibros().size());
			}
		}
		return facturaAux;
	}

}
