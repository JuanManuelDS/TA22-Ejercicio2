package controllers;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;

import models.ConexionSQL;
import views.*;

public class ControladorVista implements ActionListener {
	private ClienteFrame cframe;
	private PanelOpciones panelOpciones;
	private PanelFormularios panelFormularios;

	private ConexionSQL conSQL = new ConexionSQL();

	public ControladorVista(ClienteFrame cframe, PanelOpciones panelOpciones, PanelFormularios panelFormularios) {
		super();
		this.cframe = cframe;
		this.panelOpciones = panelOpciones;
		this.panelFormularios = panelFormularios;
		agregarEventos();
	}

	public void iniciarVista() {
		cframe.setTitle("Clientes CRUD");
		cframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cframe.setBounds(100, 100, 345, 350);
		cframe.setVisible(true);
	}

	public void agregarEventos() {
		panelOpciones.btnCrearCliente.addActionListener(this);
		panelOpciones.btnBuscarCliente.addActionListener(this);
		panelOpciones.btnEliminarCliente.addActionListener(this);
		panelOpciones.btnListarClientes.addActionListener(this);
		panelOpciones.btnModificarCliente.addActionListener(this);
		panelFormularios.crearButton.addActionListener(this);
		panelFormularios.buscarButton.addActionListener(this);
		panelFormularios.borrarButton.addActionListener(this);
		panelFormularios.actualizarButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (panelOpciones.btnBuscarCliente == e.getSource()) {
			firstImage(cframe.panelContainer);
			selectCard(cframe.panelFormularios, "buscar");

		} else if (panelOpciones.btnCrearCliente == e.getSource()) {
			firstImage(cframe.panelContainer);
			selectCard(cframe.panelFormularios, "crear");

		} else if (panelOpciones.btnEliminarCliente == e.getSource()) {
			firstImage(cframe.panelContainer);
			selectCard(cframe.panelFormularios, "borrar");

		} else if (panelOpciones.btnModificarCliente == e.getSource()) {
			firstImage(cframe.panelContainer);
			selectCard(cframe.panelFormularios, "actualizar");

		} else if(panelOpciones.btnListarClientes == e.getSource()) {
			conSQL.conectar();
			listar();
			conSQL.closeConnection();
			
		} else if(panelFormularios.crearButton == e.getSource()) {
			conSQL.conectar();
			crear();
			conSQL.closeConnection();
			
		} else if(panelFormularios.borrarButton == e.getSource()) {
			conSQL.conectar();
			borrar();
			conSQL.closeConnection();
			
		} else if(panelFormularios.buscarButton == e.getSource()) {
			conSQL.conectar();
			buscar();
			conSQL.closeConnection();
			
		} else if(panelFormularios.actualizarButton == e.getSource()) {
			conSQL.conectar();
			modificar();
			conSQL.closeConnection();
		}
	}

	public void selectCard(JPanel container, String carta) {
		CardLayout cl = (CardLayout) container.getLayout();
		cl.show(container, carta);
	}

	public void nextImage(JPanel container) {
		CardLayout cl = (CardLayout) container.getLayout();
		cl.next(container);
	}

	public void firstImage(JPanel container) {
		CardLayout cl = (CardLayout) container.getLayout();
		cl.first(container);
	}

	public void buscar() {
		Connection c = ConexionSQL.connection;
		int dni = Integer.parseInt(panelFormularios.buscarTextfield.getText());
		String data = "";
		try {
			String query = "SELECT * FROM clientes WHERE dni=" + dni + ";";
			Statement st = c.createStatement();
			java.sql.ResultSet resultSet;
			resultSet = st.executeQuery(query);
			while (resultSet.next()) {
				data += "<html>nombre: " + resultSet.getString("nombre");
				data += "<br/>Apellidos: " + resultSet.getString("apellido");
				data += "<br/>Direccion: " + resultSet.getString("direccion");
				data += "<br/>Dni: " + resultSet.getString("dni");
				data += "<br/>Fecha: " + resultSet.getString("fecha") + "</html>";
			}
			panelFormularios.resultadoBusqueda.setText(data);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error al insertar datos.");
		}
	}

	public void crear() {
		// INSERT VALUES
		Connection c = ConexionSQL.connection;
		try {
			String nombre = panelFormularios.crearNombre.getText();
			String apellidos = panelFormularios.crearApellido.getText();
			int dni = Integer.parseInt(panelFormularios.crearDni.getText());
			String direccion = panelFormularios.crearDireccion.getText();
			String fecha = panelFormularios.crearFecha.getText();

			String query = "INSERT INTO clientes (nombre, apellido, direccion, dni, fecha) values" + "('" + nombre
					+ "','" + apellidos + "', '" + direccion + "',' " + dni + "', '" + fecha + "');";
			System.out.println(query);
			Statement st = c.createStatement();
			st.executeUpdate(query);
			System.out.println("Datos insertados con exito!");
		} catch (SQLException | NumberFormatException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error al insertar datos.");
		}
	}

	public void borrar() {
		// DELETE
		Connection c = ConexionSQL.connection;
		try {
			int dni = Integer.parseInt(panelFormularios.borrarTexfield.getText());

			String query = "DELETE FROM clientes " + "WHERE dni=" + dni + ";";
			System.out.println(query);
			Statement st = c.createStatement();
			st.executeUpdate(query);
			System.out.println("Cliente borrado con exito!");
		} catch (SQLException | NumberFormatException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error al insertar datos.");
		}
	}

	public void modificar() {
		Connection c = ConexionSQL.connection;
		try {
			int dniActual = Integer.parseInt(panelFormularios.dniActual.getText());
			String nombre = panelFormularios.actualizarNombre.getText();
			String apellidos = panelFormularios.actualizarApellidos.getText();
			int dni = Integer.parseInt(panelFormularios.actualizarDNI.getText());
			String direccion = panelFormularios.actualizarDireccion.getText();
			String fecha = panelFormularios.actualizarFecha.getText();
			
			String query = "UPDATE clientes "+
					//"SET nombre = '"+ nombre +"', apellido= '"+ apellidos +"', dni ="+ dni +", direccion='"+ direccion +"', fecha='"+ fecha +"'"+
					"SET nombre = '"+nombre+"', apellido='"+apellidos+"', dni = "+dni+", direccion='"+direccion+"', fecha='"+ fecha +"'"+
					"WHERE dni = "+ dniActual +";";
			System.out.println(query);
			Statement st = c.createStatement();
			st.executeUpdate(query);
			System.out.println("Datos insertados con exito!");
		}catch(SQLException | NumberFormatException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error al insertar datos.");
		}
	}

	public void listar() {
		Connection c = ConexionSQL.connection;
		String data = "";
		try {
			String query = "SELECT * FROM clientes;";
			Statement st = c.createStatement();
			java.sql.ResultSet resultSet;
			resultSet = st.executeQuery(query);
			while (resultSet.next()) {
				data += "nombre: " + resultSet.getString("nombre");
				data += "<br/>Apellidos: " + resultSet.getString("apellido");
				data += "<br/>Direccion: " + resultSet.getString("direccion");
				data += "<br/>Dni: " + resultSet.getString("dni");
				data += "<br/>Fecha: " + resultSet.getString("fecha");
				data += "<br/>-----------------<br/>";
			}
			nextImage(cframe.panelContainer);
			cframe.labelResultados.setText("<html>" + data + "</html>");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error al insertar datos.");
		}
	}
}
