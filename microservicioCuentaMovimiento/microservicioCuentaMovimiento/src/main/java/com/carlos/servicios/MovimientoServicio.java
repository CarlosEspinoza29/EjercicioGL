package com.carlos.servicios;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlos.microservicioCuentaMovimiento.Movimientos;
import com.carlos.microservicioCuentaMovimiento.CuentaRepositorio;
import com.carlos.microservicioCuentaMovimiento.MovimientoRepositorio;
import com.carlos.excepciones.CuentaNoEncontradaExcepcion;
import com.carlos.excepciones.SaldoInsuficienteExcepcion;
import com.carlos.microservicioCuentaMovimiento.Cuenta;

//Servicio para movimiento
@Service
public class MovimientoServicio {
	
	private final MovimientoRepositorio movimientoRepositorio;
	private final CuentaRepositorio cuentaRepositorio;
	
	@Autowired
	public MovimientoServicio(MovimientoRepositorio movimientoRepositorio, CuentaRepositorio cuentaRepositorio) {
		this.movimientoRepositorio = movimientoRepositorio;
		this.cuentaRepositorio = cuentaRepositorio;
	}

	//Validaciones realizadas para calculos con el saldo de los movimientos
	public Movimientos guardarMovimiento(Movimientos movimiento) {
		Optional<Cuenta> cuentaOpt = cuentaRepositorio.findById(movimiento.getCuenta_id());
		if(cuentaOpt.isPresent()) {
			Cuenta cuenta = cuentaOpt.get();
			double saldo = cuenta.getSaldoInicial();
			double valorMovimiento = movimiento.getValor();
			if ("Retiro".equalsIgnoreCase(movimiento.getTipoMovimiento())) {
				if (saldo - valorMovimiento < 0) {
					throw new SaldoInsuficienteExcepcion("Saldo no disponible");
				}
				saldo -= valorMovimiento;
			} else {
				saldo += valorMovimiento;
			}
			cuenta.setSaldoInicial(saldo);
			movimiento.setSaldo(saldo);
			
			cuentaRepositorio.save(cuenta);
		}else {
			throw new CuentaNoEncontradaExcepcion("La cuenta no existe");
		}
		return movimientoRepositorio.save(movimiento);
	}
	
	public void eliminarMovimiento(Long id) {
		movimientoRepositorio.deleteById(id);
	}
	
	public List<Movimientos> obtenerTodosLosMovimientos(){
		return movimientoRepositorio.findAll();
	}
	
	public Optional<Movimientos> obtenerMovimientosId(Long id){
		return movimientoRepositorio.findById(id);
	}
	
	public Movimientos actualizarMovimiento(Long id, Movimientos movimientoNuevo){
		Optional<Movimientos> movimientoExistente = movimientoRepositorio.findById(id);
        
        if (movimientoExistente.isPresent()) {
            Movimientos movimiento = movimientoExistente.get();
            movimiento.setFecha(movimientoNuevo.getFecha());
            movimiento.setTipoMovimiento(movimientoNuevo.getTipoMovimiento());
            movimiento.setValor(movimientoNuevo.getValor());
            movimiento.setSaldo(movimientoNuevo.getSaldo());
            return movimientoRepositorio.save(movimiento);
        } else {
            return null; 
        }
	}
	
	public boolean existeMovimiento(Long id) {
		return movimientoRepositorio.existsById(id);
	}
	
	public List<Movimientos> obtenerMovimientosEntreFechas(Date fechaInicio, Date fechaFin) {
		return movimientoRepositorio.findByFechaBetween(fechaInicio, fechaFin);
    }

}
