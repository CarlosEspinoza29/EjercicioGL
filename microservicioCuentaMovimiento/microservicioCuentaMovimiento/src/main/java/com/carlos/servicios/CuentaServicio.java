package com.carlos.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlos.microservicioCuentaMovimiento.Cuenta;
import com.carlos.microservicioCuentaMovimiento.CuentaRepositorio;


//Servicio de Cuenta
@Service
public class CuentaServicio {
	
	private final CuentaRepositorio cuentaRepositorio;
	
	@Autowired
	public CuentaServicio(CuentaRepositorio cuentaRepositorio) {
		this.cuentaRepositorio = cuentaRepositorio;
	}

	
	public Cuenta guardarCuenta(Cuenta cuenta) {
		return cuentaRepositorio.save(cuenta);
	}
	
	public void eliminarCuenta(Long id) {
		cuentaRepositorio.deleteById(id);
	}
	
	public List<Cuenta> obtenerTodasLasCuenta(){
		return cuentaRepositorio.findAll();
	}
	
	public Optional<Cuenta> obtenerCuentaId(Long id){
		return cuentaRepositorio.findById(id);
	}
	
	public Cuenta actualizarCuenta(Long id, Cuenta cuentaNueva){
		return cuentaRepositorio.findById(id)
				.map(cuenta -> {
					cuenta.setNumeroCuenta(cuentaNueva.getNumeroCuenta());
					cuenta.setTipoCuenta(cuentaNueva.getTipoCuenta());
					cuenta.setSaldoInicial(cuentaNueva.getSaldoInicial());
					cuenta.setEstado(cuentaNueva.isEstado());
					cuenta.setClienteId(cuentaNueva.getClienteId());
					return cuentaRepositorio.save(cuenta);
				})
				.orElse(null);
	}
	
	public boolean existeCuenta(Long id) {
		return cuentaRepositorio.existsById(id);
	}
	
	public List<Cuenta> obtenerCuentasPorCliente(Long clienteId) {
        return cuentaRepositorio.findByClienteId(clienteId);
    }

}
