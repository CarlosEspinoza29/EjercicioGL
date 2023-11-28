package com.carlos.excepciones;


//Excepcion para saldo insuficiente
public class SaldoInsuficienteExcepcion extends RuntimeException{
	
	public SaldoInsuficienteExcepcion(String message) {
        super(message);
    }

}
