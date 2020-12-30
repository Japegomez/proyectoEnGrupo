package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import logica.BaseDatos;

/**
 * @author SpaceDefense
 *
 */
public class VentanaClasificaciones extends JFrame{
	private DefaultTableModel modelo;
	private JTable tabla;
	
	public VentanaClasificaciones(VentanaMenu vMenu) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initTabla();
		add(new JScrollPane(tabla),BorderLayout.CENTER);
		JButton bVolver = new JButton("Volver");
		add(bVolver,BorderLayout.SOUTH);
		this.pack();
		
		bVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaClasificaciones.this.dispose();
				vMenu.setVisible(true);				
			}
		});
	}
	private void initTabla() {
		modelo = new DefaultTableModel();
		modelo.addColumn("puesto");
		modelo.addColumn("nombre");
		modelo.addColumn("puntuacion");
		
		BaseDatos.rellenarTabla(modelo);
		tabla = new JTable(modelo);
		
	}
}
