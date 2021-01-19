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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logica.BaseDatos;
import logica.Usuario;

	/**
	 * Clase de la ventana de mejoras de la nave
	 */
public class VentanaMejorasNave extends JFrame {
	private JButton bVolver;
	private JTextField tfCreditosDisponibles;
	private JTextField tfCadencia;
	private JTextField tfDanio;
	private JTextField tfUlti;
	private JTextField tfVida;
	private JButton bVelocidad;
	private JButton bDanio;
	private JButton bVida;
	
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
		JLabel lMejoraVe = new JLabel("cadencia actual: ");
		tfCadencia = new JTextField(3);
		tfCadencia.setEditable(false);
		bVelocidad = new JButton("Mejorar cadencia");
		JLabel lMejoraD =  new JLabel("danio actual: ");
		tfDanio = new JTextField(3);
		tfDanio.setEditable(false);
		bDanio = new JButton("mejorar danio");
		JLabel lMejoraVi =  new JLabel("vida actual: ");
		tfVida = new JTextField(3);
		tfVida.setEditable(false);
		bVida = new JButton("mejorar vida");
		
		
		
		bVolver = new JButton("Volver");


		JPanel pMejoras = new JPanel();
		pMejoras.setLayout(new GridLayout(4, 3));
		JPanel pCreditos = new JPanel();
		
		pMejoras.add(lMejoraVe);
		pMejoras.add(tfCadencia);
		pMejoras.add(bVelocidad);
		pMejoras.add(lMejoraD);
		pMejoras.add(tfDanio);
		pMejoras.add(bDanio);
		pMejoras.add(lMejoraVi);
		pMejoras.add(tfVida);
		pMejoras.add(bVida);
		
		pCreditos.add(lCreditos);
		pCreditos.add(tfCreditosDisponibles);
		
		this.add(pCreditos, BorderLayout.NORTH);
		this.add(pMejoras, BorderLayout.CENTER);
		this.add(bVolver, BorderLayout.SOUTH);
		
		this.pack();

		bVelocidad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(usu.getCreditos()>=50) {
					if(usu.getNave().getVelocidadAtaque()>1) {
						usu.getNave().setVelocidadAtaque(usu.getNave().getVelocidadAtaque()-0.5);
						tfCadencia.setText("" + usu.getNave().getVelocidadAtaque());
						usu.quitarCreditos();
						tfCreditosDisponibles.setText("" + usu.getCreditos()); 
					}
					if(usu.getNave().getVelocidadAtaque()==1) {
						bVelocidad.setEnabled(false);
					}
				}
				else {
					JOptionPane.showMessageDialog(VentanaMejorasNave.this, "creditos insuficientes");
				}
			}
		});
		bDanio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(usu.getCreditos()>=50) {
					if(usu.getNave().getDanyoAtaque()<10) {
						usu.getNave().setDanyoAtaque(usu.getNave().getDanyoAtaque()+0.5);
						tfDanio.setText("" + usu.getNave().getDanyoAtaque());
						usu.quitarCreditos();
						tfCreditosDisponibles.setText("" + usu.getCreditos()); 
					}
					if(usu.getNave().getDanyoAtaque()==10) {
						bDanio.setEnabled(false);
					}
				}
				else {
					JOptionPane.showMessageDialog(VentanaMejorasNave.this, "creditos insuficientes");
				}
			}
		});
		bVida.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(usu.getCreditos()>=50) {
					if(usu.getNave().getVida()<20) {
						usu.getNave().setVida(usu.getNave().getVida()+1);
						tfVida.setText("" + usu.getNave().getVida());
						usu.quitarCreditos();
						tfCreditosDisponibles.setText("" + usu.getCreditos()); 
					}
					if(usu.getNave().getVida()==20) {
						bVida.setEnabled(false);
					}
				}
				else {
					JOptionPane.showMessageDialog(VentanaMejorasNave.this, "creditos insuficientes");
				}
			}
		});
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
				tfCreditosDisponibles.setText("" + usu.getCreditos());
				tfCadencia.setText(""+ usu.getNave().getVelocidadAtaque());
				tfDanio.setText(""+ usu.getNave().getDanyoAtaque());
				tfVida.setText(""+ usu.getNave().getVida());
				if(usu.getNave().getVelocidadAtaque()==1) {
					bVelocidad.setEnabled(false);
				}
				if(usu.getNave().getDanyoAtaque()==10) {
					bDanio.setEnabled(false);
				}
				if(usu.getNave().getVida()==20) {
					bVida.setEnabled(false);
				}
			}
		
			@Override
			public void windowClosed(WindowEvent e) {
				BaseDatos.setNave(usu);
				BaseDatos.setCreditosUsuario(usu);
			}
		});
	}
}
