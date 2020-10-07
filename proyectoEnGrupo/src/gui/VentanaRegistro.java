package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;

public class VentanaRegistro extends JFrame {

	private JPanel pPrincipal;
	private JTextField tfUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistro frame = new VentanaRegistro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaRegistro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		pPrincipal = new JPanel();
		pPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pPrincipal);
		pPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lUsuario = new JLabel("USUSARIO");
		pPrincipal.add(lUsuario);
		
		tfUsuario = new JTextField();
		tfUsuario.setText("INTRODUCE EL USUARIO");
		pPrincipal.add(tfUsuario);
		tfUsuario.setColumns(10);
	}

}
