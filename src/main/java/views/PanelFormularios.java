package views;

import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelFormularios extends JPanel {
	
	public JTextField crearNombre;
	public JTextField crearApellido;
	public JTextField crearDireccion;
	public JTextField crearDni;
	public JTextField crearFecha;
	public JTextField buscarTextfield;
	public JTextField borrarTexfield;
	public JTextField actualizarNombre;
	public JTextField actualizarApellidos;
	public JTextField actualizarDireccion;
	public JTextField actualizarDNI;
	public JTextField actualizarFecha;
	public JTextField dniActual;
	public JButton crearButton;
	public JButton buscarButton;
	public JButton borrarButton;
	public JButton actualizarButton;
	public JButton listarButton;
	private JLabel lblDniActual;
	public JLabel resultadoBusqueda;

	public PanelFormularios() {
		setLayout(new CardLayout(0, 0));
		
		/*--------------FORMULARIO CREAR -------------------*/
		JPanel formularioCrear = new JPanel();
		add(formularioCrear, "crear");
		formularioCrear.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre: ");
		lblNewLabel.setBounds(10, 11, 73, 14);
		formularioCrear.add(lblNewLabel);
		
		crearNombre = new JTextField();
		crearNombre.setBounds(10, 24, 124, 20);
		formularioCrear.add(crearNombre);
		crearNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido: ");
		lblApellido.setBounds(10, 55, 73, 14);
		formularioCrear.add(lblApellido);
		
		crearApellido = new JTextField();
		crearApellido.setBounds(10, 68, 124, 20);
		formularioCrear.add(crearApellido);
		crearApellido.setColumns(10);
		
		JLabel lblDireccin = new JLabel("Dirección: ");
		lblDireccin.setBounds(10, 99, 63, 14);
		formularioCrear.add(lblDireccin);
		
		crearDireccion = new JTextField();
		crearDireccion.setColumns(10);
		crearDireccion.setBounds(10, 112, 124, 20);
		formularioCrear.add(crearDireccion);
		
		JLabel lblDni = new JLabel("DNI: ");
		lblDni.setBounds(10, 143, 63, 14);
		formularioCrear.add(lblDni);
		
		crearDni = new JTextField();
		crearDni.setColumns(10);
		crearDni.setBounds(10, 155, 124, 20);
		formularioCrear.add(crearDni);
		
		crearFecha = new JTextField();
		crearFecha.setColumns(10);
		crearFecha.setBounds(10, 199, 124, 20);
		formularioCrear.add(crearFecha);
		
		JLabel lblFecha = new JLabel("Fecha: ");
		lblFecha.setBounds(10, 186, 63, 14);
		formularioCrear.add(lblFecha);
		
		crearButton = new JButton("Crear");
		crearButton.setBounds(10, 235, 89, 23);
		formularioCrear.add(crearButton);
		
		/*--------------FORMULARIO BUSCAR --------------------*/
		JPanel formularioBuscar = new JPanel();
		add(formularioBuscar, "buscar");
		formularioBuscar.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("DNI de la persona: ");
		lblNewLabel_1.setBounds(10, 11, 133, 14);
		formularioBuscar.add(lblNewLabel_1);
		
		buscarTextfield = new JTextField();
		buscarTextfield.setBounds(10, 24, 133, 20);
		formularioBuscar.add(buscarTextfield);
		buscarTextfield.setColumns(10);
		
		buscarButton = new JButton("Buscar");
		buscarButton.setBounds(10, 55, 89, 23);
		formularioBuscar.add(buscarButton);
		
		resultadoBusqueda = new JLabel("");
		resultadoBusqueda.setBounds(10, 89, 133, 151);
		formularioBuscar.add(resultadoBusqueda);
		
		/*--------------FORMULARIO BORRAR -----------------*/
		JPanel formularioBorrar = new JPanel();
		add(formularioBorrar, "borrar");
		formularioBorrar.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("DNI de la persona");
		lblNewLabel_2.setBounds(10, 11, 130, 14);
		formularioBorrar.add(lblNewLabel_2);
		
		borrarTexfield = new JTextField();
		borrarTexfield.setBounds(10, 25, 130, 20);
		formularioBorrar.add(borrarTexfield);
		borrarTexfield.setColumns(10);
		
		borrarButton = new JButton("Borrar");
		borrarButton.setBounds(10, 56, 89, 23);
		formularioBorrar.add(borrarButton);
		
		/*------------FORMULARIO ACTUALIZAR ----------------------*/
		JPanel formularioActualizar = new JPanel();
		add(formularioActualizar, "actualizar");
		formularioActualizar.setLayout(null);
		
		JLabel labelNombre = new JLabel("Nombre: ");
		labelNombre.setBounds(14, 64, 93, 14);
		formularioActualizar.add(labelNombre);
		
		actualizarNombre = new JTextField();
		actualizarNombre.setBounds(14, 80, 120, 20);
		formularioActualizar.add(actualizarNombre);
		actualizarNombre.setColumns(10);
		
		JLabel labelApellido = new JLabel("Apellido: ");
		labelApellido.setBounds(14, 104, 69, 14);
		formularioActualizar.add(labelApellido);
		
		actualizarApellidos = new JTextField();
		actualizarApellidos.setBounds(14, 120, 120, 20);
		formularioActualizar.add(actualizarApellidos);
		actualizarApellidos.setColumns(10);
		
		JLabel direccionLabel = new JLabel("Dirección: ");
		direccionLabel.setBounds(14, 142, 69, 14);
		formularioActualizar.add(direccionLabel);
		
		actualizarDireccion = new JTextField();
		actualizarDireccion.setColumns(10);
		actualizarDireccion.setBounds(14, 157, 120, 20);
		formularioActualizar.add(actualizarDireccion);
		
		JLabel dniLabel = new JLabel("DNI: ");
		dniLabel.setBounds(14, 181, 49, 14);
		formularioActualizar.add(dniLabel);
		
		actualizarDNI = new JTextField();
		actualizarDNI.setColumns(10);
		actualizarDNI.setBounds(14, 198, 120, 20);
		formularioActualizar.add(actualizarDNI);
		
		actualizarFecha = new JTextField();
		actualizarFecha.setColumns(10);
		actualizarFecha.setBounds(14, 235, 120, 20);
		formularioActualizar.add(actualizarFecha);
		
		JLabel fechaLabel = new JLabel("Fecha: ");
		fechaLabel.setBounds(14, 219, 49, 14);
		formularioActualizar.add(fechaLabel);
		
		actualizarButton = new JButton("Actualizar");
		actualizarButton.setBounds(14, 266, 108, 23);
		formularioActualizar.add(actualizarButton);
		
		dniActual = new JTextField();
		dniActual.setColumns(10);
		dniActual.setBounds(14, 35, 120, 20);
		formularioActualizar.add(dniActual);
		
		lblDniActual = new JLabel("DNI actual: ");
		lblDniActual.setBounds(14, 21, 93, 14);
		formularioActualizar.add(lblDniActual);
		
		
		
		
	}

}
