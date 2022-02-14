package ar.edu.unju.fi.examen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ar.edu.unju.fi.examen.service.imp.UsuarioDetailServiceDetailImp;



/**
 * WebSecurityCondig es una clase heredada de  WebSecurityConfigurerAdapter
 * La usamos para sobre escribir los métodos para 
 * 1 Indicar credenciales para inicio de sesión
 * 2 Configurar acceso a urls http
 */
@Configuration
@EnableWebSecurity 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UsuarioDetailServiceDetailImp usuDetailService;
	
	@Autowired
	AutSuccessHandler autSuccesHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/**
		* Construir detalle del objeto que debe tomar spring security para construir un objeto cliente
		*passwordEncoder(passwordEnconder(): descifrar contraseña
		*/
		auth.userDetailsService(usuDetailService).passwordEncoder(passwordEnconder());
	}

	
	/**
	 * Establece permisos para acceso a las distintas páginas
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/**
		 * .authorizeRequests().antMatchers().permitAll: permite indicar qué recursos tienen acceso libre
		 * 
		 * .formLogin().loginPage("/login") indico cuál es la página de login a usar
		 ***********
		 * .successHandler(null): manipula la redirección cuando el inicio de sesión fue
		 * correcto, se encarga de los permisos, según qué roles posea un usuario se mostrarán ciertas páginas
		 *	creo una clase especial para especificar esto
		 ***********
		 * .failureUrl("/login?error=true"):
		 * 
		 * 
		 */
		http
			.authorizeRequests()
				.antMatchers("/include/**","/css/**","icons/**","/images/**","/js/**","/layer/**","/webjars/**","/layout/**").permitAll()  
				.anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/login")
				.successHandler(autSuccesHandler)
				.failureUrl("/login?error=true")
				.permitAll()
			.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/logout")
		;
	}

	
	/**
	 * Método agregado para encriptar la contraseña del cliente
	 * @return devuelve un objeto de tipo BCryptPasswordEncoder que permitirá usar 
	 * algún método para la encriptación de contraseñas
	 */
	@Bean
	public BCryptPasswordEncoder passwordEnconder() {
		return new BCryptPasswordEncoder();
	}
	
}
