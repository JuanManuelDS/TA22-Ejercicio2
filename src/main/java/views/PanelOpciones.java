package views;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class PanelOpciones extends JPanel{
	
	public JButton btnCrearCliente;
	public JButton btnEliminarCliente;
	public JButton btnModificarCliente;
	public JButton btnListarClientes;
	public JButton btnBuscarCliente;
	public JComboBox comboBox;
	
	
	public PanelOpciones() {
		
		
		setBounds(10, 11, 122, 239);
		setLayout(new GridLayout(7, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Operación a realizar");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.addItem("clientes");
		comboBox.addItem("Video");
		add(comboBox);
		
		btnCrearCliente = new JButton("Crea");
		add(btnCrearCliente);
		
		btnEliminarCliente = new JButton("Eliminar");
		add(btnEliminarCliente);
		
		btnModificarCliente = new JButton("Modificar");
		add(btnModificarCliente);
		
		btnListarClientes = new JButton("Listar");
		add(btnListarClientes);
		
		btnBuscarCliente = new JButton("Buscar");
		add(btnBuscarCliente);
		
		
		
	}
	
}
