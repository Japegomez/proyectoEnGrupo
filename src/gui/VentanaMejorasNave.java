package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class VentanaMejorasNave extends JFrame {
	private JButton mejVelocidad;
	private JButton mejDanio;
	private JButton mejUlti;
	private JButton mejVida;
	private JPanel pMejoras;
	private JButton bVolver;
	private JLabel lCreditos;
	private JTextField tfCreditosDisponibles;
	private JPanel pCreditos;
	
	/**Crea una ventana para poder mejorar la nave del usuario
	 * @param vMenu VentanaMenu de la que precede
	 */
	public VentanaMejorasNave(VentanaMenu vMenu) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		
		pMejoras = new JPanel();
		pMejoras.setLayout(new GridLayout(2, 2));
		mejVelocidad = new JButton("mejorar velocidad");
		mejDanio = new JButton("mejorar danio");
		mejUlti = new JButton("mejorar el tiempo para obtener la ulti");
		mejVida = new JButton("mejorar vida");
		lCreditos = new JLabel("Creditos disponibles: ");
		tfCreditosDisponibles = new JTextField(15);
		tfCreditosDisponibles.setEditable(false);
		bVolver = new JButton("Volver");
		pCreditos = new JPanel();
		
		pMejoras.add(mejVelocidad);
		pMejoras.add(mejDanio);
		pMejoras.add(mejUlti);
		pMejoras.add(mejVida);
		
		pCreditos.add(lCreditos);
		pCreditos.add(tfCreditosDisponibles);
		
		this.add(pCreditos, BorderLayout.NORTH);
		this.add(pMejoras, BorderLayout.CENTER);
		this.add(bVolver, BorderLayout.SOUTH);
		
		this.pack();
		
		bVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaMejorasNave.this.dispose();
				vMenu.setVisible(true);
				
			}
		});
	}
}
