package com.carlos.microservicioCuentaMovimiento;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Repositorio movimiento
@Repository
public interface MovimientoRepositorio extends JpaRepository<Movimientos, Long> {
	//Proceso para encontrar movimientos entre las fechas indicadas
	List<Movimientos> findByFechaBetween(Date fechaInicio, Date fechaFin);

}
