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

import com.carlos.microservicioCuentaMovimiento.Cuenta;
import com.carlos.servicios.CuentaServicio;

//Controlador REST para cuentas
@RestController
@RequestMapping("/cuentas")
public class CuentaControlador {
	
	private CuentaServicio cuentaServicio;
	
	@Autowired
	public CuentaControlador(CuentaServicio cuentaServicio) {
		this.cuentaServicio = cuentaServicio;
	}

	@PostMapping
	public ResponseEntity<Cuenta> crearCuenta(@RequestBody Cuenta cuenta){
		Cuenta cuentaNuevo = cuentaServicio.guardarCuenta(cuenta);
		return new ResponseEntity<>(cuentaNuevo, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarCuenta(@PathVariable Long id){
		if(!cuentaServicio.existeCuenta(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		cuentaServicio.eliminarCuenta(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping
	public ResponseEntity<List<Cuenta>> obtenerCuentas(){
		List<Cuenta> cuentas = cuentaServicio.obtenerTodasLasCuenta();
		return new ResponseEntity<>(cuentas, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cuenta> obtenerCuentaId(@PathVariable Long id){
		return cuentaServicio.obtenerCuentaId(id)
				.map(cuenta -> new ResponseEntity<>(cuenta, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable Long id, @RequestBody Cuenta cuenta){
		if(!cuentaServicio.existeCuenta(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Cuenta cuentaCambiado = cuentaServicio.actualizarCuenta(id, cuenta);
		return new ResponseEntity<>(cuentaCambiado, HttpStatus.OK);
	}

}
