package com.modelos;

public class Cliente {

	private String cedula;
	private String nombre;
	private String email;

	public Cliente(String id, String nombre, String email) {
		this.cedula = id;
		this.nombre = nombre;
		this.email = email;
	}

	public String getCedula() {
		return cedula;
	}

	public void setId(String id) {
		this.cedula = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
