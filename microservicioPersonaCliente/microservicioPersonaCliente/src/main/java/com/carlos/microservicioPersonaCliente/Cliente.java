package com.carlos.microservicioPersonaCliente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

//Entidad cliente
@Entity
public class Cliente extends Persona {
	
	@Column(unique=true)
	private String clienteid;
	
	private String contrasena;
	private boolean estado;
	
	private Long persona_id;
	
	public Cliente() {
		
	}

	public Cliente(String nombre, String genero, int edad, Long identificacion, String direccion, String telefono,
			String clienteid, String contrasena, boolean estado, Long persona_id) {
		super(nombre, genero, edad, identificacion, direccion, telefono);
		this.clienteid = clienteid;
		this.contrasena = contrasena;
		this.estado = estado;
		this.persona_id = persona_id;
	}

	public String getClienteId() {
		return clienteid;
	}

	public void setClienteId(String clienteId) {
		this.clienteid = clienteId;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Long getPersona_id() {
		return persona_id;
	}

	public void setPersona_id(Long persona_id) {
		this.persona_id = persona_id;
	}
	
	

}
