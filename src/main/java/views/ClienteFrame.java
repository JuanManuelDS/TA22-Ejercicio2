package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JLabel;

import java.awt.FlowLayout;

public class ClienteFrame extends JFrame {

	public JPanel contentPane;
	public JPanel panelOpciones;
	public JPanel panelFormularios;
	public JPanel panelContainer;
	public JPanel panelResultados;
	public JLabel labelResultados;


	public ClienteFrame(JPanel optionPanel, JPanel formPanel) {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*-------PANEL OPCIONES --------------------*/
		panelOpciones = optionPanel;
		
		/*-----------PANEL CONTAINER FORMULARIOS Y RESULTADOS --------------*/
		panelContainer = new JPanel();
		panelContainer.setBounds(142, 11, 177, 299);
		
		panelContainer.setLayout(new CardLayout(0, 0));
		
		JScrollPane panelContainerResultados = new JScrollPane();
		
		
		panelResultados = new JPanel();
		panelResultados.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelContainerResultados.setViewportView(panelResultados);
		
		labelResultados = new JLabel("");
		panelResultados.add(labelResultados);
		
		panelFormularios = formPanel;
		panelContainer.add(panelFormularios);
		panelContainer.add(panelContainerResultados);
		
		contentPane.add(panelOpciones);
		contentPane.add(panelContainer);
	}
}
