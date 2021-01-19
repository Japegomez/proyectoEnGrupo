package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import logica.BaseDatos;

/**Clase para crear y actualizar la ventana de las clasificaciones
 * @author SpaceDefense
 *
 */
public class VentanaClasificaciones extends JFrame{
	private DefaultTableModel modelo;
	private JTable tabla;
	
/**Constructor de la ventana de clasificacion
 * @param ventana principal donde se encontrara la ventana de las clasificaciones
 */
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

/**Clase para inicializar la tabla que aparecera en la ventana
 * @author SpaceDefense
 */
	private void initTabla() {
		modelo = new DefaultTableModel();
		modelo.addColumn("puesto");
		modelo.addColumn("nombre");
		modelo.addColumn("puntuacion");
		
		BaseDatos.rellenarTabla(modelo);
		tabla = new JTable(modelo);
		
	}
}
