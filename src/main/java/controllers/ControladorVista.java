package controllers;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
	private boolean crudCliente;

	private ConexionSQL conSQL = new ConexionSQL();

	public ControladorVista(ClienteFrame cframe, PanelOpciones panelOpciones, PanelFormularios panelFormularios) {
		super();
		this.cframe = cframe;
		this.panelOpciones = panelOpciones;
		this.panelFormularios = panelFormularios;
		crudCliente = true;
		agregarEventos();
	}

	public void iniciarVista() {
		cframe.setTitle("Clientes CRUD");
		cframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cframe.setBounds(100, 100, 345, 350);
		cframe.setVisible(true);
	}

	public void agregarEventos() {
		panelOpciones.comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				changeCrudViews();
			}
		});
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
			if(crudCliente) {
				listarClientes();
			}else {
				listarVideos();
			}
			conSQL.closeConnection();
			
		} else if(panelFormularios.crearButton == e.getSource()) {
			conSQL.conectar();
			if(crudCliente) {
				crearCliente();
			} else {
				crearVideo();
			}
			conSQL.closeConnection();
			
		} else if(panelFormularios.borrarButton == e.getSource()) {
			conSQL.conectar();
			if(crudCliente) {
				borrarCliente();
			} else {
				borrarVideo();
			}
			conSQL.closeConnection();
			
		} else if(panelFormularios.buscarButton == e.getSource()) {
			conSQL.conectar();
			if(crudCliente) {
				buscarCliente();
			} else {
				buscarVideo();
			}
			conSQL.closeConnection();
			
		} else if(panelFormularios.actualizarButton == e.getSource()) {
			conSQL.conectar();
			if(crudCliente) {
				modificarCliente();
			} else {
				modificarVideo();
			}
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

	public void buscarCliente() {
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

	public void crearCliente() {
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

	public void borrarCliente() {
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

	public void modificarCliente() {
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

	public void listarClientes() {
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

	
	public void changeCrudViews() {
		if(panelOpciones.comboBox.getSelectedIndex() == 0) {
			crudCliente = true;
		} else if(panelOpciones.comboBox.getSelectedIndex() == 1) {
			crudCliente=false;
		}
		changeForms();
	}
	
	public void changeForms() {
		//Oculto/desoculto labels y textfields según elección comboBox
		//CREAR FORM CHANGE
		panelFormularios.lblDni.setVisible(crudCliente);
		panelFormularios.crearDni.setVisible(crudCliente);
		panelFormularios.lblFecha.setVisible(crudCliente);
		panelFormularios.crearFecha.setVisible(crudCliente);
		
		//UPDATE FORM CHANGE
		panelFormularios.fechaLabel.setVisible(crudCliente);
		panelFormularios.actualizarFecha.setVisible(crudCliente);
		panelFormularios.dniLabel.setVisible(crudCliente);
		panelFormularios.actualizarDNI.setVisible(crudCliente);
		
		if(!crudCliente) {
			//CREAR FORM CHANGE
			panelFormularios.lnlNombre.setText("Título: ");
			panelFormularios.lblApellido.setText("Director: ");
			panelFormularios.lblDireccin.setText("Id del cliente: ");
			
			//UPDATE FORM CHANGE
			panelFormularios.lblDniActual.setText("Título actual: ");
			panelFormularios.labelNombre.setText("Título: ");
			panelFormularios.labelApellido.setText("Director: ");
			panelFormularios.direccionLabel.setText("Id Cliente: ");
			
			//BUSCAR
			panelFormularios.lblDniBuscar.setText("Título de la película: ");
			
			//ELIMINAR
			panelFormularios.lblDniBorrar.setText("Título de la película: ");
			
		} else {
			//CREAR FORM CHANGE
			panelFormularios.lnlNombre.setText("Nombre: ");
			panelFormularios.lblApellido.setText("Apellido: ");
			panelFormularios.lblDireccin.setText("Direccion: ");
			
			//UPDATE FORM CHANGE
			panelFormularios.lblDniActual.setText("DNI actual: ");
			panelFormularios.labelNombre.setText("Nombre: ");
			panelFormularios.labelApellido.setText("Apellido: ");
			panelFormularios.direccionLabel.setText("Dirección: ");
			
			//BUSCAR
			panelFormularios.lblDniBuscar.setText("DNI de la persona: ");
			
			//ELIMINAR
			panelFormularios.lblDniBorrar.setText("DNI de la persona: ");
		}
	}

	//------------------------------METODOS VIDEO---------------------------------------
	public void buscarVideo() {
		Connection c = ConexionSQL.connection;
		int dni = Integer.parseInt(panelFormularios.buscarTextfield.getText());
		String data = "";
		try {
			String query = "SELECT * FROM clientes WHERE dni=" + dni + ";";
			Statement st = c.createStatement();
			java.sql.ResultSet resultSet;
			resultSet = st.executeQuery(query);
			while (resultSet.next()) {
				data += "<html>Titulo: " + resultSet.getString("title");
				data += "<br/>Director: " + resultSet.getString("director");
				data += "<br/>ID Cliente: " + resultSet.getString("cli_id")+ "</html>";
			}
			panelFormularios.resultadoBusqueda.setText(data);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error al insertar datos.");
		}
	}
	
	public void crearVideo() {
		// INSERT VALUES
		Connection c = ConexionSQL.connection;
		try {
			String title = panelFormularios.crearNombre.getText();
			String director = panelFormularios.crearApellido.getText();
			int cli_id = Integer.parseInt(panelFormularios.crearDireccion.getText());

			String query = "INSERT INTO videos (title, director, cli_id) values" + "('" + title
					+ "','" + director + "', '" + cli_id + "');";
			System.out.println(query);
			Statement st = c.createStatement();
			st.executeUpdate(query);
			System.out.println("Datos insertados con exito!");
		} catch (SQLException | NumberFormatException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error al insertar datos.");
		}
	}
	
	public void borrarVideo() {
		// DELETE
		Connection c = ConexionSQL.connection;
		try {
			String tituloActual = panelFormularios.lblDniBorrar.getText();
			String query = "DELETE FROM videos " + "WHERE title=" + tituloActual + ";";
			System.out.println(query);
			Statement st = c.createStatement();
			st.executeUpdate(query);
			System.out.println("Cliente borrado con exito!");
		} catch (SQLException | NumberFormatException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error al insertar datos.");
		}
	}
	
	public void modificarVideo() {
		Connection c = ConexionSQL.connection;
		try {
			String tituloActual = panelFormularios.dniActual.getText();
			String title = panelFormularios.actualizarNombre.getText();
			String director = panelFormularios.actualizarApellidos.getText();
			int cli_id = Integer.parseInt(panelFormularios.actualizarDireccion.getText());
			
			String query = "UPDATE videos "+
					"SET title = '"+title+"', director='"+director+"', cli_id = "+cli_id+
					"WHERE dni = "+ tituloActual +";";
			System.out.println(query);
			Statement st = c.createStatement();
			st.executeUpdate(query);
			System.out.println("Datos insertados con exito!");
		}catch(SQLException | NumberFormatException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error al insertar datos.");
		}
	}
	
	public void listarVideos() {
		Connection c = ConexionSQL.connection;
		String data = "";
		try {
			String query = "SELECT * FROM videos;";
			Statement st = c.createStatement();
			java.sql.ResultSet resultSet;
			resultSet = st.executeQuery(query);
			while (resultSet.next()) {
				data += "Titulo: " + resultSet.getString("title");
				data += "<br/>Director: " + resultSet.getString("director");
				data += "<br/>ID Cliente: " + resultSet.getString("cli_id");
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
