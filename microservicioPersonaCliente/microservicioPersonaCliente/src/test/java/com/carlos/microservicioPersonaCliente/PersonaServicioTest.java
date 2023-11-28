package com.carlos.microservicioPersonaCliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
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

import com.carlos.servicios.PersonaServicio;

public class PersonaServicioTest {
	
	@Mock
    private PersonaRepositorio personaRepositorioMock;

    @InjectMocks
    private PersonaServicio personaServicio;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void guardarPersonaTest() {
    	
        Persona persona = new Persona();
        persona.setNombre("Jose Lema");
        persona.setGenero("Masculino");
        persona.setEdad(35);
        persona.setIdentificacion(123456789L);
        persona.setDireccion("Calle Test");
        persona.setTelefono("123");

        when(personaRepositorioMock.save(persona)).thenReturn(persona);

        Persona resultado = personaServicio.guardarPersona(persona);

        verify(personaRepositorioMock, times(1)).save(persona);
        assertEquals(persona.getNombre(), resultado.getNombre());
    }
    
    @Test
    public void eliminarPersonaTest() {
    	
        doNothing().when(personaRepositorioMock).deleteById(1L);

        personaServicio.eliminarPersona(1L);

        verify(personaRepositorioMock, times(1)).deleteById(1L);
    }
    
    @Test
    public void obtenerTodasLasPersonasTest() {

    	Persona persona1 = new Persona();
        persona1.setNombre("Jose Lema");
        persona1.setGenero("Masculino");
        persona1.setEdad(35);
        persona1.setIdentificacion(123456789L);
        persona1.setDireccion("Calle Test");
        persona1.setTelefono("123");
        Persona persona2 = new Persona();
        persona2.setNombre("Marianel Montalvo ");
        persona2.setGenero("Femenino");
        persona2.setEdad(30);
        persona2.setIdentificacion(9876L);
        persona2.setDireccion("Calle Prueba");
        persona2.setTelefono("456");
        List<Persona> listaPersonas = Arrays.asList(persona1, persona2);

        when(personaRepositorioMock.findAll()).thenReturn(listaPersonas);

        List<Persona> resultado = personaServicio.obtenerTodasLasPersonas();

        verify(personaRepositorioMock, times(1)).findAll();
        assertEquals(2, resultado.size());
    }
    
    @Test
    public void obtenerPersonaIdTest() {

    	Persona persona = new Persona();
    	persona.setNombre("Juan Osorio");
        persona.setGenero("Masculino");
        persona.setEdad(32);
        persona.setIdentificacion(1L);
        persona.setDireccion("Calle Test");
        persona.setTelefono("123");

        when(personaRepositorioMock.findById(1L)).thenReturn(Optional.of(persona));

        Optional<Persona> resultado = personaServicio.obtenerPersonaId(1L);

        verify(personaRepositorioMock, times(1)).findById(1L);
        assertTrue(resultado.isPresent());
        assertEquals(persona.getIdentificacion(), resultado.get().getIdentificacion());
    }
    
    @Test
    public void actualizarPersonaTest() {

    	Persona persona = new Persona();
    	persona.setNombre("Juan Osorio");
        persona.setGenero("Masculino");
        persona.setEdad(32);
        persona.setIdentificacion(1L);
        persona.setDireccion("Calle Test");
        persona.setTelefono("123");

        when(personaRepositorioMock.findById(1L)).thenReturn(Optional.of(persona));
        when(personaRepositorioMock.save(persona)).thenReturn(persona);

        Persona personaActualizada = new Persona();
        personaActualizada.setNombre("Carlos Test");
        Persona resultado = personaServicio.actualizarPersona(1L, personaActualizada);

        verify(personaRepositorioMock, times(1)).findById(1L);
        verify(personaRepositorioMock, times(1)).save(persona);
        assertEquals(personaActualizada.getNombre(), resultado.getNombre());
    }

    @Test
    public void existePersonaTest() {

    	when(personaRepositorioMock.existsById(1L)).thenReturn(true);

        boolean resultado = personaServicio.existePersona(1L);

        verify(personaRepositorioMock, times(1)).existsById(1L);
        assertTrue(resultado);
    }

}
