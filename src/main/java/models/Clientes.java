package models;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Clientes {
	// M�todo para crear la tabla
	public void createTable() {
		Connection c = ConexionSQL.connection;
		try {
			// Borrar la tabla en caso que exista
			String query = "DROP TABLE IF EXISTS clientes";
			Statement st = c.createStatement();
			st.executeUpdate(query);
			// Crear la tabla y su estructura
			query = "CREATE TABLE clientes(" 
					+ "id INT(11) NOT NULL AUTO_INCREMENT, "
					+ "nombre VARCHAR(1250) DEFAULT NULL, " 
					+ "apellido VARCHAR(1250) DEFAULT NULL, " 
					+ "direccion VARCHAR(1250) DEFAULT NULL, " 
					+ "dni INT(11) DEFAULT NULL," 
					+ "fecha date DEFAULT NULL,"
					+ "PRIMARY KEY (id)"
					+ ");";
			st.executeUpdate(query);
			System.out.println("Tabla creada con éxito!");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error creando la tabla.");
		}
	}
	
	// M�todo para insertar datos en la tabla
		public void insertClientes() {
			Connection c = ConexionSQL.connection;
			try {
				String query = "INSERT INTO clientes (nombre, apellido, direccion, dni, fecha) values"+
						"('Juan','Sanchez','Margarita 1',34561234,'2022-03-12'),"+
						"('Maria','Domingo','Rosa 21',12563478,'2022-06-18'),"+
						"('Pedro','Domingo','Margarita 1',87654321,'2022-03-12');";
			
				Statement st = c.createStatement();
				st.executeUpdate(query);
				System.out.println("Datos insertados con exito!");
			}catch(SQLException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Error al insertar datos.");
			}
		}
}
