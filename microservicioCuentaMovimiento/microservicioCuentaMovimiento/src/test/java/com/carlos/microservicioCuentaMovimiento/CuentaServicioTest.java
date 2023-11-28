package com.carlos.microservicioCuentaMovimiento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.carlos.servicios.CuentaServicio;

public class CuentaServicioTest {
	
	@Mock
    private CuentaRepositorio cuentaRepositorioMock;

    @InjectMocks
    private CuentaServicio cuentaServicio;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void guardarCuentaTest() {

    	Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta("123456");
        cuenta.setTipoCuenta("Ahorro");
        cuenta.setSaldoInicial(1000.0);
        cuenta.setEstado(true);
        cuenta.setClienteId(1L);

        when(cuentaRepositorioMock.save(cuenta)).thenReturn(cuenta);

        Cuenta resultado = cuentaServicio.guardarCuenta(cuenta);

        verify(cuentaRepositorioMock, times(1)).save(cuenta);
        assertNotNull(resultado);
        assertEquals(cuenta.getNumeroCuenta(), resultado.getNumeroCuenta());
    }
    
    @Test
    public void eliminarCuentaTest() {
    	
        Long idCuenta = 1L;

        cuentaServicio.eliminarCuenta(idCuenta);

        verify(cuentaRepositorioMock, times(1)).deleteById(idCuenta);
    }
    
    @Test
    public void obtenerTodasLasCuentaTest() {

    	Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta("123456");
        cuenta.setTipoCuenta("Ahorro");
        cuenta.setSaldoInicial(1000.0);
        cuenta.setEstado(true);
        cuenta.setClienteId(1L);
        
        Cuenta cuenta2 = new Cuenta();
        cuenta2.setNumeroCuenta("78910");
        cuenta2.setTipoCuenta("Corriente");
        cuenta2.setSaldoInicial(1000.0);
        cuenta2.setEstado(true);
        cuenta2.setClienteId(1L);

        List<Cuenta> cuentas = Arrays.asList(cuenta, cuenta2);

        when(cuentaRepositorioMock.findAll()).thenReturn(cuentas);

        List<Cuenta> resultado = cuentaServicio.obtenerTodasLasCuenta();

        verify(cuentaRepositorioMock, times(1)).findAll();
        assertNotNull(resultado);
        assertEquals(2, resultado.size()); 
    }
    
    @Test
    public void obtenerCuentaIdExistenteTest() {

    	Cuenta cuenta = new Cuenta();
    	Long cuentaId = 1L;
        cuenta.setNumeroCuenta("1");
        cuenta.setTipoCuenta("Ahorro");
        cuenta.setSaldoInicial(1000.0);
        cuenta.setEstado(true);
        cuenta.setClienteId(1L);

        when(cuentaRepositorioMock.findById(cuentaId)).thenReturn(Optional.of(cuenta));

        Optional<Cuenta> resultado = cuentaServicio.obtenerCuentaId(cuentaId);

        verify(cuentaRepositorioMock, times(1)).findById(cuentaId);
        assertTrue(resultado.isPresent());
        assertEquals(cuenta.getNumeroCuenta(), resultado.get().getNumeroCuenta());
    }
    
    @Test
    public void actualizarCuentaExistenteTest() {

    	Cuenta cuenta = new Cuenta();
    	Long cuentaId = 1L;
        cuenta.setNumeroCuenta("1");
        cuenta.setTipoCuenta("Ahorro");
        cuenta.setSaldoInicial(1000.0);
        cuenta.setEstado(true);
        cuenta.setClienteId(1L);

        Cuenta cuentaActualizada = new Cuenta();
        cuentaActualizada.setId(cuentaId);
        cuentaActualizada.setTipoCuenta("Corriente");

        when(cuentaRepositorioMock.findById(cuentaId)).thenReturn(Optional.of(cuenta));
        when(cuentaRepositorioMock.save(cuenta)).thenReturn(cuentaActualizada);

        Cuenta resultado = cuentaServicio.actualizarCuenta(cuentaId, cuentaActualizada);

        verify(cuentaRepositorioMock, times(1)).findById(cuentaId);
        verify(cuentaRepositorioMock, times(1)).save(cuenta);
        assertNotNull(resultado);
        assertEquals(cuentaActualizada.getTipoCuenta(), resultado.getTipoCuenta());
    }
    
    @Test
    public void obtenerCuentasPorClienteTest() {

    	Long clienteId = 1L;
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta("1");
        cuenta.setTipoCuenta("Ahorro");
        cuenta.setSaldoInicial(1000.0);
        cuenta.setEstado(true);
        cuenta.setClienteId(1L);

        Cuenta cuenta2 = new Cuenta();
        cuenta2.setNumeroCuenta("2");
        cuenta2.setTipoCuenta("Ahorro");
        cuenta2.setSaldoInicial(1000.0);
        cuenta2.setEstado(true);
        cuenta2.setClienteId(1L);

        List<Cuenta> cuentas = Arrays.asList(cuenta, cuenta2);

        when(cuentaRepositorioMock.findByClienteId(clienteId)).thenReturn(cuentas);

        List<Cuenta> resultado = cuentaServicio.obtenerCuentasPorCliente(clienteId);

        verify(cuentaRepositorioMock, times(1)).findByClienteId(clienteId);
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("1", resultado.get(0).getNumeroCuenta());
        assertEquals("Ahorro", resultado.get(0).getTipoCuenta());
    }


}
