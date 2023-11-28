package com.carlos.microservicioCuentaMovimiento;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

//Entidad movimientos
@Entity
public class Movimientos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
	private String tipoMovimiento;
	private double valor;
	private double saldo;
	
    private Long cuenta_id;
	
	public Movimientos() {
		
	}

	public Movimientos(Date fecha, String tipoMovimiento, double valor, double saldo, Long cuenta_id) {
		this.fecha = fecha;
		this.tipoMovimiento = tipoMovimiento;
		this.valor = valor;
		this.saldo = saldo;
		this.cuenta_id =cuenta_id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Long getCuenta_id() {
		return cuenta_id;
	}

	public void setCuenta_id(Long cuenta_id) {
		this.cuenta_id = cuenta_id;
	}
	
	

}
