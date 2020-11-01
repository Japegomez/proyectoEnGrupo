package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author japen
 *
 */
/**
 * @author japen
 *
 */
/**
 * @author japen
 *
 */
public class VentanaClasificaciones extends JFrame{
	private JPanel pNombre1puesto;
	private JLabel l1puesto;
	private JTextField tfNombre1puesto;
	
	private JPanel pPuntos1puesto;
	private JLabel l1puntos;
	private JTextField tfPuntos1puesto;
	
	private JPanel pNombre2puesto;
	private JLabel l2puesto;
	private JTextField tfNombre2puesto;
	

	private JPanel pPuntos2puesto;
	private JLabel l2puntos;
	private JTextField tfPuntos2puesto;
	
	private JPanel pNombre3puesto;
	private JLabel l3puesto;
	private JTextField tfNombre3puesto;
	
	private JPanel pPuntos3puesto;
	private JLabel l3puntos;
	private JTextField tfPuntos3puesto;

	

	/**
	 * @return Nombre del usuario con la mejor puntuacion
	 */
	public JTextField getTfNombre1puesto() {
		return tfNombre1puesto;
	}

	/**Modifica el textfield con el nombre del usuario con la mejor marca
	 * @param tfNombre1puesto Nombre del nuevo usuario con la mejor marca
	 */
	public void setTfNombre1puesto(JTextField tfNombre1puesto) {
		this.tfNombre1puesto = tfNombre1puesto;
	}
	/**
	 * @return Puntos del usuario con la mejor puntuacion
	 */
	public JTextField getTfPuntos1puesto() {
		return tfPuntos1puesto;
	}
	/**Modifica el textfield con los puntos del usuario con la mejor marca
	 * @param tfPuntos1puesto Puntos del nuevo usuario con la mejor marca
	 */
	public void setTfPuntos1puesto(JTextField tfPuntos1puesto) {
		this.tfPuntos1puesto = tfPuntos1puesto;
	}
	/**
	 * @return Nombre del usuario con la segunda mejor puntuacion
	 */
	public JTextField getTfNombre2puesto() {
		return tfNombre2puesto;
	}
	/**Modifica el textfield con el nombre del usuario con la segunda mejor marca
	 * @param tfNombre2puesto Nombre del nuevo usuario con la segunda mejor marca
	 */
	public void setTfNombre2puesto(JTextField tfNombre2puesto) {
		this.tfNombre2puesto = tfNombre2puesto;
	}
	/**
	 * @return Puntos del usuario con la segunda mejor puntuacion
	 */
	public JTextField getTfPuntos2puesto() {
		return tfPuntos2puesto;
	}
	/**Modifica el textfield con los puntos del usuario con la segunda mejor marca
	 * @param tfPuntos2puesto Puntos del nuevo usuario con la segunda mejor marca
	 */
	public void setTfPuntos2puesto(JTextField tfPuntos2puesto) {
		this.tfPuntos2puesto = tfPuntos2puesto;
	}
	/**
	 * @return Nombre del usuario con la tercera mejor puntuacion
	 */
	public JTextField getTfNombre3puesto() {
		return tfNombre3puesto;
	}
	/**Modifica el textfield con el nombre del usuario con la tercera mejor marca
	 * @param tfNombre3puesto Nombre del nuevo usuario con la tercera mejor marca
	 */
	public void setTfNombre3puesto(JTextField tfNombre3puesto) {
		this.tfNombre3puesto = tfNombre3puesto;
	}
	/**
	 * @return Puntos del usuario con la tercera mejor  puntuacion
	 */
	public JTextField getTfPuntos3puesto() {
		return tfPuntos3puesto;
	}
	/**Modifica el textfield con los puntos del usuario con la tercera mejor marca
	 * @param tfPuntos3puesto Puntos del nuevo usuario con la tercera mejor marca
	 */
	public void setTfPuntos3puesto(JTextField tfPuntos3puesto) {
		this.tfPuntos3puesto = tfPuntos3puesto;
	}

	
	/**Crea una ventana que muestra la clasificacion
	 * @param vMenu VentaMenu de la que precede
	 */
	public VentanaClasificaciones(VentanaMenu vMenu){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
	
		pNombre1puesto = new JPanel();
		l1puesto = new JLabel("primer puesto:");
		tfNombre1puesto = new JTextField(15);
		tfNombre1puesto.setEditable(false);
		pPuntos1puesto = new JPanel();
		l1puntos = new JLabel("puntos:");
		tfPuntos1puesto = new JTextField(10);
		tfPuntos1puesto.setEditable(false);
		
		pNombre2puesto = new JPanel();
		l2puesto = new JLabel("segundo puesto:");
		tfNombre2puesto = new JTextField(15);
		tfNombre2puesto.setEditable(false);
		pPuntos2puesto = new JPanel();
		l2puntos = new JLabel("puntos:");
		tfPuntos2puesto = new JTextField(10);
		tfPuntos2puesto.setEditable(false);
		
		pNombre3puesto = new JPanel();
		l3puesto = new JLabel("tercer puesto:");
		tfNombre3puesto = new JTextField(15);
		tfNombre3puesto.setEditable(false);
		pPuntos3puesto = new JPanel();
		l3puntos = new JLabel("puntos:");
		tfPuntos3puesto = new JTextField(10);
		tfPuntos3puesto.setEditable(false);
		
		JButton bVolver = new JButton("Volver");
		
		pNombre1puesto.add(l1puesto);
		pNombre1puesto.add(tfNombre1puesto);
		pPuntos1puesto.add(l1puntos);
		pPuntos1puesto.add(tfPuntos1puesto);
		
		pNombre2puesto.add(l2puesto);
		pNombre2puesto.add(tfNombre2puesto);
		pPuntos2puesto.add(l2puntos);
		pPuntos2puesto.add(tfPuntos2puesto);
		
		pNombre3puesto.add(l3puesto);
		pNombre3puesto.add(tfNombre3puesto);
		pPuntos3puesto.add(l3puntos);
		pPuntos3puesto.add(tfPuntos3puesto);
		
		this.add(pNombre1puesto);
		this.add(pPuntos1puesto);
		
		
		this.add(pNombre2puesto);
		this.add(pPuntos2puesto);
		
		
		this.add(pNombre3puesto);
		this.add(pPuntos3puesto);
	
		this.add(bVolver);
		
		this.pack();
		
		
		bVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaClasificaciones.this.dispose();
				vMenu.setVisible(true);
				
				
			}
		});
	}
}
