package ar.edu.unju.fi.examen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.edu.unju.fi.examen.modelo.Factura;

public interface IVentaDAO extends JpaRepository<Factura, Long>{

	@Query("from Factura f order by f.codigo")
	public List<Factura> getFacturas();
}
