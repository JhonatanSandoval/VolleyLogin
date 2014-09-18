package com.example.modelo;

public class Cliente {

	private String idCliente;
	private String cliente;
	private String correo;
	private String celular;
	
	public Cliente(){
		
	}

	public Cliente(String idCliente, String cliente, String correo,
			String celular) {
		super();
		this.idCliente = idCliente;
		this.cliente = cliente;
		this.correo = correo;
		this.celular = celular;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

}
