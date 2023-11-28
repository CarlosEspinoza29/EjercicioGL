package com.carlos.microservicioCuentaMovimiento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//Respositorio de cuenta
@Repository
public interface CuentaRepositorio extends JpaRepository<Cuenta, Long> {
	
	//Proceso para encontrar cliente por el Id
	@Query("SELECT c FROM Cuenta c WHERE c.cliente_id = :clienteId")
    List<Cuenta> findByClienteId(@Param("clienteId") Long clienteId);

}
