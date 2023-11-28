package com.carlos.microservicioPersonaCliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Repositorio para persona
@Repository
public interface PersonaRepositorio extends JpaRepository<Persona, Long> {

}
