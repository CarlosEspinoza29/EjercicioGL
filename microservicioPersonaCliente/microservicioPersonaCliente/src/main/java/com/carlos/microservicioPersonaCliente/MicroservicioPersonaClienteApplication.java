package com.carlos.microservicioPersonaCliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
	    "com.carlos.controladores",
	    "com.carlos.servicios",
	    "com.carlos.configs",
	    "com.carlos.microservicioclientepersona"
	})
public class MicroservicioPersonaClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioPersonaClienteApplication.class, args);
	}

}
