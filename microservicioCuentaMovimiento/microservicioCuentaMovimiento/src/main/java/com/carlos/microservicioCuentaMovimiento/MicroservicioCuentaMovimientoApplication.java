package com.carlos.microservicioCuentaMovimiento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
	    "com.carlos.controladores",
	    "com.carlos.servicios",
	    "com.carlos.config",
	    "com.carlos.microservicioclientepersona"
	})
public class MicroservicioCuentaMovimientoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioCuentaMovimientoApplication.class, args);
	}

}
