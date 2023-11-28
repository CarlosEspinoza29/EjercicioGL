package com.carlos.microservicioPersonaCliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Repositorio para cliente
@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

}
