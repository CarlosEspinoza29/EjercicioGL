package com.carlos.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlos.microservicioPersonaCliente.Persona;
import com.carlos.microservicioPersonaCliente.PersonaRepositorio;

//Servicio para persona
@Service
public class PersonaServicio {
	
	@Autowired
	private PersonaRepositorio personaRepositorio;
	
		public Persona guardarPersona(Persona persona) {
			return personaRepositorio.save(persona);
		}
		
		public void eliminarPersona(Long id) {
			personaRepositorio.deleteById(id);
		}
		
		public List<Persona> obtenerTodasLasPersonas(){
			return personaRepositorio.findAll();
		}
		
		public Optional<Persona> obtenerPersonaId(Long id) {
			return personaRepositorio.findById(id);
		}
		
		public Persona actualizarPersona(Long id, Persona personaNueva) {
			return personaRepositorio.findById(id)
					.map(persona ->{
						persona.setNombre(personaNueva.getNombre());
						persona.setGenero(personaNueva.getGenero());
						persona.setEdad(personaNueva.getEdad());
						persona.setIdentificacion(personaNueva.getIdentificacion());
						persona.setDireccion(personaNueva.getDireccion());
						persona.setTelefono(personaNueva.getTelefono());
						return personaRepositorio.save(persona);
					})
					.orElse(null);
		}
		
		public boolean existePersona(Long id) {
			return personaRepositorio.existsById(id);
		}

}
