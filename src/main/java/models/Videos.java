package models;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Videos {
	// M�todo para crear la tabla
	public void createTable() {
		Connection c = ConexionSQL.connection;
		try {
			// Borrar la tabla en caso que exista
			String query = "DROP TABLE IF EXISTS videos";
			Statement st = c.createStatement();
			st.executeUpdate(query);
			// Crear la tabla y su estructura
			query = "CREATE TABLE videos(" 
					+ "id INT(11) NOT NULL AUTO_INCREMENT, "
					+ "title VARCHAR(250) DEFAULT NULL, " 
					+ "director VARCHAR(250) DEFAULT NULL, "
					+ "cli_id INT(11) DEFAULT NULL,"
					+ "PRIMARY KEY (id)," 
					+ "CONSTRAINT FKClienteVideo FOREIGN KEY (cli_id) REFERENCES clientes (id)"
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
			String query = "INSERT INTO videos (title, director, cli_id) values"
					+ "('Star Wars IV', 'George Lucas', 1),"
					+ "('Pacific Rim', 'Guillermo del Toro', 2),"
					+ "('El Señor de los Anillos', 'Peter Jackson', 3);";

			Statement st = c.createStatement();
			st.executeUpdate(query);
			System.out.println("Datos insertados con exito!");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error al insertar datos.");
		}
	}
}
