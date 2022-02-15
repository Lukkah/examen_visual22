package ar.edu.unju.fi.examen.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.examen.modelo.Cliente;
import ar.edu.unju.fi.examen.repository.IClienteDAO;
import ar.edu.unju.fi.examen.service.IClienteService;
import ar.edu.unju.fi.examen.util.Util;

@Service
public class UsuarioDetailServiceDetailImp implements UserDetailsService{
	private static final Log LOGGER = LogFactory.getLog("UsuarioDetailServiceDetailImp");
	
	@Autowired
	IClienteService clienteServiceImp;
	
	@Autowired
	IClienteDAO clienteDaoImp;
	
	
	/**
	 * Recupero el usuario(cliente) de la base de datos para construir 
	 * con él, un "UserDetail"
	 * Este método permite guardar el usuario y contraseña correctos, junto con los permisos
	 * que posea (sus roles), para luego verificar que los datos de 
	 * inicio de sesión sean correctos
	 * 
	 * Se utiliza en el AutenticationManagerBuilder de la clase WebSecurityConfig
	 * 
	 * param username, es el nombre de usuario que se ingresa en el momento de iniciar sesión
	 * return la construccion del usuario(cliente) en caso de encontrarlo, o sino 
	 * null en caso de que no exista, junto con una excepción
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Busco en la base de datos un usuario con este nombre
		Cliente cliente = clienteDaoImp.findByUsuario(username);
		
		Util.clienteUtil = clienteDaoImp.findByUsuario(username);
		LOGGER.info("************************* Cliente que inició sesión login ********************************");

		LOGGER.info(clienteDaoImp.findByUsuario(username).getApellido());
		
		
		//Clase que construirá los detalles de la clase cliente para la autenticación
		UserBuilder builder = null;
		
		//Si se encuentra al cliente puedo armar los detalles
		if(cliente != null) {
			builder = User.withUsername(username);
			builder.password(cliente.getPassword());
			
			//builer necesita un listado de "authotities", osea un grupo de entidades autorizadas
			//Suelen ser roles de tipos de usuario: ej admin, socio, vendedor, cliente, etc.
			
			List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
			roles.add(new SimpleGrantedAuthority(cliente.getEstado()));
			builder.authorities(roles);
			
		}else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		
		return builder.build();
	}

}
