package com.carlos.microservicioPersonaCliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
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

import com.carlos.servicios.ClienteServicio;

public class ClienteServicioTest {
	
	@Mock
    private ClienteRepositorio clienteRepositorioMock;

    @Mock
    private PersonaRepositorio personaRepositorioMock;

    @InjectMocks
    private ClienteServicio clienteServicio;
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void guardarClienteExistenteTest() {

    	Cliente cliente = new Cliente();
        cliente.setClienteId("123");
        cliente.setContrasena("password");
        cliente.setEstado(true);
        cliente.setPersona_id(1L);

        Persona persona = new Persona();
        persona.setNombre("Jose Lema");
        persona.setGenero("Masculino");
        persona.setEdad(35);
        persona.setIdentificacion(123456789L);
        persona.setDireccion("Calle Test");
        persona.setTelefono("123");

        when(personaRepositorioMock.findById(1L)).thenReturn(Optional.of(persona));
        when(clienteRepositorioMock.save(cliente)).thenReturn(cliente);

        Cliente resultado = clienteServicio.guardarCliente(cliente);

        verify(personaRepositorioMock, times(1)).findById(1L);
        verify(clienteRepositorioMock, times(1)).save(cliente);
        assertNotNull(resultado);
        assertEquals(cliente.getClienteId(), resultado.getClienteId());
    }
    
    @Test
    public void guardarClienteNoExistenteTest() {

    	Cliente cliente = new Cliente();
    	cliente.setClienteId("1");
        cliente.setContrasena("password");
        cliente.setEstado(true);
        cliente.setPersona_id(1L);

        when(personaRepositorioMock.findById(anyLong())).thenReturn(Optional.empty());

        Cliente resultado = clienteServicio.guardarCliente(cliente);

        verify(personaRepositorioMock, times(1)).findById(anyLong());
        verify(clienteRepositorioMock, never()).save(cliente);
        assertNull(resultado);
    }
    
    @Test
    public void eliminarClienteExistenteTest() {
        Long clienteId = 1L;

        clienteServicio.eliminarCliente(clienteId);

        verify(clienteRepositorioMock, times(1)).deleteById(clienteId);
    }
    
    @Test
    public void obtenerTodosLosClientesTest() {
    	Cliente cliente = new Cliente();
    	cliente.setClienteId("1");
        cliente.setContrasena("password");
        cliente.setEstado(true);
        cliente.setPersona_id(1L);

        Cliente cliente2 = new Cliente();
        cliente.setClienteId("2");
        cliente.setContrasena("cont");
        cliente.setEstado(true);
        cliente.setPersona_id(1L);

        List<Cliente> listaClientes = Arrays.asList(cliente, cliente2);

        when(clienteRepositorioMock.findAll()).thenReturn(listaClientes);

        List<Cliente> resultado = clienteServicio.obtenerTodosLosClientes();

        verify(clienteRepositorioMock, times(1)).findAll();
        assertNotNull(resultado);
        assertEquals(2, resultado.size()); 
    }
    
    @Test
    public void obtenerClienteIdExistenteTest() {
    	Cliente cliente = new Cliente();
    	cliente.setClienteId("1");
        cliente.setContrasena("password");
        cliente.setEstado(true);
        cliente.setPersona_id(1L);

        when(clienteRepositorioMock.findById(1L)).thenReturn(Optional.of(cliente));

        Optional<Cliente> resultado = clienteServicio.obtenerClienteId(1L);

        verify(clienteRepositorioMock, times(1)).findById(1L);
        assertNotNull(resultado);
        assertTrue(resultado.isPresent());
        assertEquals(cliente.getPersona_id(), resultado.get().getPersona_id());
        assertEquals(cliente.getClienteId(), resultado.get().getClienteId());
    }
    
    @Test
    public void actualizarClienteExistenteTest() {

    	Cliente cliente = new Cliente();
    	cliente.setClienteId("1");
        cliente.setContrasena("password");
        cliente.setEstado(true);
        cliente.setPersona_id(1L);

        Cliente clienteActualizado = new Cliente();
        clienteActualizado.setClienteId("1");
        clienteActualizado.setPersona_id(1L);
        clienteActualizado.setContrasena("newPassword");

        when(clienteRepositorioMock.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepositorioMock.save(cliente)).thenReturn(clienteActualizado);

        Cliente resultado = clienteServicio.actualizarCliente(1L, clienteActualizado);

        verify(clienteRepositorioMock, times(1)).findById(1L);
        verify(clienteRepositorioMock, times(1)).save(cliente);
        assertNotNull(resultado);
        assertEquals(clienteActualizado.getContrasena(), resultado.getContrasena());
        assertEquals(clienteActualizado.getPersona_id(), resultado.getPersona_id());
        assertEquals(clienteActualizado.getClienteId(), resultado.getClienteId());
    }
    
    @Test
    public void existeClienteExistenteTest() {

    	Long idCliente = 1L;

        when(clienteRepositorioMock.existsById(idCliente)).thenReturn(true);

        boolean existeCliente = clienteServicio.existeCliente(idCliente);

        verify(clienteRepositorioMock, times(1)).existsById(idCliente);
        assertTrue(existeCliente);
    }

}
