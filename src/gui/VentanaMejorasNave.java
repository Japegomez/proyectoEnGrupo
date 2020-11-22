package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logica.Usuario;


public class VentanaMejorasNave extends JFrame {
	private JButton bVolver;
	private JTextField tfCreditosDisponibles;
	private JTextField tfVelocidad;
	private JTextField tfDanio;
	private JTextField tfUlti;
	private JTextField tfVida;
	private JButton bVelocidad;
	private JButton bDanio;
	private JButton bUlti;
	private JButton bVida;
	private Thread actualizaCreditosDisponibles;
	
	/**Crea una ventana para poder mejorar la nave del usuario
	 * @param vMenu VentanaMenu de la que precede
	 */
	public VentanaMejorasNave(VentanaMenu vMenu, Usuario usu) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		JLabel lCreditos = new JLabel("creditos disponibles: ");
		tfCreditosDisponibles = new JTextField(15);
		tfCreditosDisponibles.setEditable(false);
		JLabel lMejoraVe = new JLabel("velocidad actual: ");
		tfVelocidad = new JTextField(3);
		bVelocidad = new JButton("Mejorar velocidad");
		JLabel lMejoraD =  new JLabel("danio actual: ");
		tfDanio = new JTextField(3);
		bDanio = new JButton("mejorar danio");
		JLabel lMejoraU =  new JLabel("cooldown ulti actual: ");
		tfUlti = new JTextField(3);
		bUlti = new JButton("mejorar cooldown ulti");
		JLabel lMejoraVi =  new JLabel("vida actual: ");
		tfVida = new JTextField(3);
		bVida = new JButton("mejorar vida");
		
		
		
		bVolver = new JButton("Volver");


		JPanel pMejoras = new JPanel();
		pMejoras.setLayout(new GridLayout(4, 3));
		JPanel pCreditos = new JPanel();
		
		pMejoras.add(lMejoraVe);
		pMejoras.add(tfVelocidad);
		pMejoras.add(bVelocidad);
		pMejoras.add(lMejoraD);
		pMejoras.add(tfDanio);
		pMejoras.add(bDanio);
		pMejoras.add(lMejoraU);
		pMejoras.add(tfUlti);
		pMejoras.add(bUlti);
		pMejoras.add(lMejoraVi);
		pMejoras.add(tfVida);
		pMejoras.add(bVida);
		
		pCreditos.add(lCreditos);
		pCreditos.add(tfCreditosDisponibles);
		
		this.add(pCreditos, BorderLayout.NORTH);
		this.add(pMejoras, BorderLayout.CENTER);
		this.add(bVolver, BorderLayout.SOUTH);
		
		this.pack();
		actualizaCreditosDisponibles = new Thread() {
			@Override
				public void run() {
					tfCreditosDisponibles.setText("" + usu.getCreditos()); 
					tfVelocidad.setText("" + usu.getNave().getVelocidadAtaque());
					try {
						Thread.sleep(20);
					} catch (Exception e) {
					}
				

		}};
		bVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaMejorasNave.this.dispose();
				vMenu.setVisible(true);
				
			}
		});
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				actualizaCreditosDisponibles.start();
			}
	
			@Override
			public void windowClosed(WindowEvent e) {
				actualizaCreditosDisponibles.interrupt();
			}
		});
	}
}
