package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class VentanaLogin extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lNombreUsuario;
	private JTextField tfNombreUsuario;
	private JLabel lContrasenya;
	private JTextField tfContrasenya;
	private JButton bLogin;
	private JButton bRegistro;
	
	public VentanaLogin(String titulo) {
		super(titulo);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		JPanel pNombre = new JPanel();
		JPanel pContrasenia = new JPanel();
		JPanel pBotonera = new JPanel();
		
		
		lNombreUsuario = new JLabel("Nombre de Usuario");
		tfNombreUsuario = new JTextField("Introduce el nombre de Usuario");
		
		lContrasenya = new JLabel("Contrasenya");
		tfContrasenya = new JTextField("Introduce la Contrasenya");
		
		bLogin = new JButton("Login");
		bRegistro = new JButton("Registrarse");
		
		pNombre.add(lNombreUsuario);
		pNombre.add(tfNombreUsuario);
		pContrasenia.add(lContrasenya);
		pContrasenia.add(tfContrasenya);
		pBotonera.add(bLogin);
		pBotonera.add(bRegistro);
		this.add(pNombre);
		this.add(pContrasenia);
		this.add(pBotonera);
		
		this.pack();
		
		
		bLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaMenu vMenu = new VentanaMenu("menu");
				vMenu.setVisible(true);
				VentanaLogin.this.setVisible(false);
			}
		});
		
		
		
		
	}
	
	
}
