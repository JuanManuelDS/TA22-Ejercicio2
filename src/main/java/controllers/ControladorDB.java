package controllers;

import models.*;

public class ControladorDB {
	private ConexionSQL modelo;
	private Clientes c;
	private Videos v;
	
	public ControladorDB(ConexionSQL modelo, Clientes c, Videos v) {
		this.modelo = modelo;
		this.c = c;
		this.v = v;
	}

	public void iniciarDB() {
		modelo.conectar();
		modelo.crearDB();
		c.createTable();
		c.insertClientes();
		v.createTable();
		
		//modelo.closeConnection();
	}
	
	
}
