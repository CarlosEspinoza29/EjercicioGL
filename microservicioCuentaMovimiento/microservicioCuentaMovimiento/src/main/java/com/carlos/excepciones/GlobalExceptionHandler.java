package com.carlos.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

//Handles para las excepciones
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(SaldoInsuficienteExcepcion.class)
    public ResponseEntity<Object> handleSaldoInsuficienteException(SaldoInsuficienteExcepcion ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CuentaNoEncontradaExcepcion.class)
    public ResponseEntity<Object> handleCuentaNotFoundException(CuentaNoEncontradaExcepcion ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
