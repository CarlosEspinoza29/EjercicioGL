package com.carlos.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlos.microservicioPersonaCliente.Persona;
import com.carlos.servicios.PersonaServicio;

//Controlador para persona
@RestController
@RequestMapping("/personas")
public class PersonaControlador {
	
	@Autowired
	private PersonaServicio personaServicio;
	
	@PostMapping
	public ResponseEntity<Persona> crearPersona(@RequestBody Persona persona){
		Persona personaNueva = personaServicio.guardarPersona(persona);
		return new ResponseEntity<>(personaNueva, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPersona(@PathVariable Long id){
		if(!personaServicio.existePersona(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		personaServicio.eliminarPersona(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping
	public ResponseEntity<List<Persona>> obtenerPersonas(){
		List<Persona> personas = personaServicio.obtenerTodasLasPersonas();
		return new ResponseEntity<>(personas, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Persona> obtenerPersonaId(@PathVariable Long id){
		return personaServicio.obtenerPersonaId(id)
				.map(persona -> new ResponseEntity<>(persona, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Persona> actualizarPersona(@PathVariable Long id, @RequestBody Persona persona){
		if(!personaServicio.existePersona(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Persona personaCambiada = personaServicio.actualizarPersona(id, persona);
		return new ResponseEntity<>(personaCambiada, HttpStatus.OK);
	}

}
