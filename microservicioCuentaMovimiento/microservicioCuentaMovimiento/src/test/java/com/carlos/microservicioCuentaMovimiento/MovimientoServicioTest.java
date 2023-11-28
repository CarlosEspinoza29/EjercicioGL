package com.carlos.microservicioCuentaMovimiento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.carlos.servicios.MovimientoServicio;

public class MovimientoServicioTest {
	
	@Mock
    private MovimientoRepositorio movimientoRepositorioMock;

    @Mock
    private CuentaRepositorio cuentaRepositorioMock;

    @InjectMocks
    private MovimientoServicio movimientoServicio;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void guardarMovimientoTest() {

    	Movimientos movimiento = new Movimientos();
        movimiento.setFecha(new Date());
        movimiento.setTipoMovimiento("Retiro");
        movimiento.setValor(100.0);
        movimiento.setSaldo(500.0);
        movimiento.setCuenta_id(1L);

        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setNumeroCuenta("1");
        cuenta.setTipoCuenta("Ahorros");
        cuenta.setSaldoInicial(500.0);
        cuenta.setEstado(true);
        cuenta.setClienteId(1L);

        when(cuentaRepositorioMock.findById(1L)).thenReturn(Optional.of(cuenta));
        when(movimientoRepositorioMock.save(movimiento)).thenReturn(movimiento);

        Movimientos resultado = movimientoServicio.guardarMovimiento(movimiento);

        verify(cuentaRepositorioMock, times(1)).findById(1L);
        verify(movimientoRepositorioMock, times(1)).save(movimiento);
        assertNotNull(resultado);
        assertEquals(movimiento.getTipoMovimiento(), resultado.getTipoMovimiento());
        assertEquals(movimiento.getSaldo(), resultado.getSaldo());
    }
    
    @Test
    public void obtenerMovimientosEntreFechasTest() {
        Date fechaInicio = new Date();
        Date fechaFin = new Date(); 

        List<Movimientos> movimientos = Arrays.asList(
                new Movimientos(new Date(), "Retiro", 100.0, 500.0, 1L),
                new Movimientos(new Date(), "Deposito", 200.0, 700.0, 2L)
        );

        when(movimientoRepositorioMock.findByFechaBetween(fechaInicio, fechaFin)).thenReturn(movimientos);

        List<Movimientos> resultado = movimientoServicio.obtenerMovimientosEntreFechas(fechaInicio, fechaFin);

        verify(movimientoRepositorioMock, times(1)).findByFechaBetween(fechaInicio, fechaFin);
        assertNotNull(resultado);
        assertEquals(movimientos.size(), resultado.size());
    }

}
