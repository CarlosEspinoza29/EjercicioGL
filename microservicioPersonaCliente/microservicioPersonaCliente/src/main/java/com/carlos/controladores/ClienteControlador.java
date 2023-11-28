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

import com.carlos.microservicioPersonaCliente.Cliente;
import com.carlos.servicios.ClienteServicio;

//Controlador para cliente
@RestController
@RequestMapping("/clientes")
public class ClienteControlador {
	
	private final ClienteServicio clienteServicio;
	
	@Autowired
	public ClienteControlador(ClienteServicio clienteServicio) {
		this.clienteServicio = clienteServicio;
	}
	
	@PostMapping
	public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente){
		Cliente clienteNuevo = clienteServicio.guardarCliente(cliente);
		return new ResponseEntity<>(clienteNuevo, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarCliente(@PathVariable Long id){
		if(!clienteServicio.existeCliente(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		clienteServicio.eliminarCliente(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping
	public ResponseEntity<List<Cliente>> obtenerClientes(){
		List<Cliente> clientes = clienteServicio.obtenerTodosLosClientes();
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> obtenerClienteId(@PathVariable Long id){
		return clienteServicio.obtenerClienteId(id)
				.map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente){
		if(!clienteServicio.existeCliente(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Cliente clienteCambiado = clienteServicio.actualizarCliente(id, cliente);
		return new ResponseEntity<>(clienteCambiado, HttpStatus.OK);
	}

}
