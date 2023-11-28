package com.carlos.excepciones;

//Excepcion para cuenta no encontrada
public class CuentaNoEncontradaExcepcion extends RuntimeException{
	
	public CuentaNoEncontradaExcepcion(String message) {
        super(message); 
    }

}
