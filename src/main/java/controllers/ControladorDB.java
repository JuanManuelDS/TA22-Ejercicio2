package controllers;

import models.*;

public class ControladorDB {
	private ConexionSQL modelo;
	private Clientes c;
	
	public ControladorDB(ConexionSQL modelo, Clientes c) {
		this.modelo = modelo;
		this.c = c;
	}

	public void iniciarDB() {
		modelo.conectar();
		modelo.crearDB();
		c.createTable();
		c.insertClientes();
		//modelo.closeConnection();
	}
	
	
}
