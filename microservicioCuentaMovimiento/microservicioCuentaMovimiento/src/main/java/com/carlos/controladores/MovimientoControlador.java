package com.carlos.controladores;

//Controlador REST para Movimiento
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carlos.microservicioCuentaMovimiento.Movimientos;
import com.carlos.servicios.MovimientoServicio;

@RestController
@RequestMapping("/movimientos")
public class MovimientoControlador {
	
	private MovimientoServicio movimientoServicio;
	
	@Autowired
	public MovimientoControlador(MovimientoServicio movimientoServicio) {
		this.movimientoServicio = movimientoServicio;
	}
	
	@PostMapping
	public ResponseEntity<Movimientos> crearMovimientos(@RequestBody Movimientos movimientos){
		Movimientos movimientosNuevo = movimientoServicio.guardarMovimiento(movimientos);
		return new ResponseEntity<>(movimientosNuevo, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarMovimientos(@PathVariable Long id){
		if(!movimientoServicio.existeMovimiento(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		movimientoServicio.eliminarMovimiento(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping
	public ResponseEntity<List<Movimientos>> obtenerMovimientoss(){
		List<Movimientos> movimientoss = movimientoServicio.obtenerTodosLosMovimientos();
		return new ResponseEntity<>(movimientoss, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Movimientos> obtenerMovimientosId(@PathVariable Long id){
		return movimientoServicio.obtenerMovimientosId(id)
				.map(movimientos -> new ResponseEntity<>(movimientos, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Movimientos> actualizarMovimientos(@PathVariable Long id, @RequestBody Movimientos movimientos){
		if(!movimientoServicio.existeMovimiento(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Movimientos movimientosCambiado = movimientoServicio.actualizarMovimiento(id, movimientos);
		return new ResponseEntity<>(movimientosCambiado, HttpStatus.OK);
	}
	
	@GetMapping("/reportes")
    public ResponseEntity<List<Movimientos>> obtenerMovimientosEnRangoDeFechas(
    		
		@RequestParam("fechaInicio") String fechaInicioStr,
        @RequestParam("fechaFin") String fechaFinStr) {

        LocalDate fechaInicio = LocalDate.parse(fechaInicioStr);
        LocalDate fechaFin = LocalDate.parse(fechaFinStr);

        Date fechaInicioUtil = java.sql.Date.valueOf(fechaInicio);
        Date fechaFinUtil = java.sql.Date.valueOf(fechaFin);

        List<Movimientos> movimientos = movimientoServicio.obtenerMovimientosEntreFechas(fechaInicioUtil, fechaFinUtil);

        return new ResponseEntity<>(movimientos, HttpStatus.OK);
            
    }

}
