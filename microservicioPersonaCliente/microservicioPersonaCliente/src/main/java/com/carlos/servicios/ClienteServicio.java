package com.carlos.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlos.microservicioPersonaCliente.Cliente;
import com.carlos.microservicioPersonaCliente.ClienteRepositorio;
import com.carlos.microservicioPersonaCliente.Persona;
import com.carlos.microservicioPersonaCliente.PersonaRepositorio;

//Servicio para cliente
@Service
public class ClienteServicio {
	
	private final ClienteRepositorio clienteRepositorio;
	private final PersonaRepositorio personaRepositorio;
	
	@Autowired
	public ClienteServicio(ClienteRepositorio clienteRepositorio, PersonaRepositorio personaRepositorio) {
		this.clienteRepositorio = clienteRepositorio;
		this.personaRepositorio = personaRepositorio;
	}
	
	
	public Cliente guardarCliente(Cliente cliente) {
		Optional<Persona> personaAsociada = personaRepositorio.findById(cliente.getPersona_id());
		if (personaAsociada.isPresent()) {
            Persona persona = personaAsociada.get();
            cliente.setNombre(persona.getNombre());
            cliente.setGenero(persona.getGenero());
            cliente.setEdad(persona.getEdad());
            cliente.setDireccion(persona.getDireccion());
            cliente.setTelefono(persona.getTelefono());
            return clienteRepositorio.save(cliente);
        } else {
            return null;
        }
	}
	
	public void eliminarCliente(Long id) {
		clienteRepositorio.deleteById(id);
	}
	
	public List<Cliente> obtenerTodosLosClientes(){
		return clienteRepositorio.findAll();
	}
	
	public Optional<Cliente> obtenerClienteId(Long id) {
		return clienteRepositorio.findById(id);
	}
	
	public Cliente actualizarCliente(Long id, Cliente clienteNuevo) {
		return clienteRepositorio.findById(id)
				.map(cliente ->{
					cliente.setNombre(clienteNuevo.getNombre());
					cliente.setGenero(clienteNuevo.getGenero());
					cliente.setEdad(clienteNuevo.getEdad());
					cliente.setEstado(clienteNuevo.isEstado());
					cliente.setPersona_id(clienteNuevo.getPersona_id());
					return clienteRepositorio.save(cliente);
				})
				.orElse(null);
	}
	
	public boolean existeCliente(Long id) {
		return clienteRepositorio.existsById(id);
	}

}
