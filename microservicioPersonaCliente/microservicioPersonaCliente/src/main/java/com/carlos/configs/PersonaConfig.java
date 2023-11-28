package com.carlos.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carlos.controladores.PersonaControlador;
import com.carlos.servicios.PersonaServicio;

//Config con beans de persona
@Configuration
public class PersonaConfig {
	
	@Bean
	public PersonaServicio personaservicio() {
		return new PersonaServicio();
	}
	
	@Bean
	public PersonaControlador personaControlador() {
		return new PersonaControlador();
	}
	
}
