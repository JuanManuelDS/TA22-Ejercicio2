package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import utils.*;

public class ConexionSQL {

	public static Connection connection;
	private Credentials credentials = new Credentials();

	public void conectar() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Password retirada por seguridad
			connection = DriverManager.getConnection(
					"jdbc:mysql://" + credentials.getIp() + ":3306?useTimezone=true&serverTimezone=UTC",
					credentials.getUsuario(), credentials.getPassword());

			// Indicar la base de datos
			String querydb = "USE clientesVideo;";
			Statement stdb = connection.createStatement();
			stdb.executeUpdate(querydb);

			System.out.println("Server Connected");
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("No se ha podido conectar con la base de datos.");
			System.out.println(ex);
		}
	}

	public void crearDB() {
		try {
			// Eliminar la base de datos en caso que exista
			String query = "DROP DATABASE IF EXISTS clientesVideo";
			Statement st = connection.createStatement();
			st.executeUpdate(query);
			// Crear la base de datos desde cero
			query = "CREATE DATABASE clientesVideo";
			st.executeUpdate(query);
			// Indicar la base de datos
			String querydb = "USE clientesVideo;";
			Statement stdb = connection.createStatement();
			stdb.executeUpdate(querydb);

			System.out.println("Base de datos creada.");
		} catch (SQLException ex) {
			System.out.println("No se ha podido crear la base de datos.");
			Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	// M�todo para cerrar la conexi�n con la base de datos
	public void closeConnection() {
		try {
			connection.close();
			System.out.println("Se ha finalizado la conexi�n con el servidor");
		} catch (SQLException ex) {
			Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
