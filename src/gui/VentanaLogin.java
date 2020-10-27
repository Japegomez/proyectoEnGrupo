package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import logica.BaseDatos;
import logica.Usuario;

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
	private ArrayList< Usuario> arrayUsuarios= new ArrayList<Usuario>();
	
	public VentanaLogin(String titulo) {		
		super(titulo);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		JPanel pNombre = new JPanel();
		JPanel pContrasenia = new JPanel();
		JPanel pBotonera = new JPanel();
		
		
		lNombreUsuario = new JLabel("Usuario");
		tfNombreUsuario = new JTextField(15);
		
		lContrasenya = new JLabel("Contrasenya");
		tfContrasenya = new JTextField(15);
		
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
		
		Usuario usu1 = new Usuario();
		arrayUsuarios.add(usu1);
		Usuario usu2 = new Usuario();
		usu2.setNombreUsuario("Xabi");
		
		
		bLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		bRegistro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BaseDatos.registrarUsuario(tfNombreUsuario.getText(), tfContrasenya.getText());
			}
		});
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				BaseDatos.abrirConexion("baseDatos.bd");	
			}
		
			
			@Override
			public void windowClosed(WindowEvent e) {
				BaseDatos.cerrarConexion();
				VentanaMenu vMenu = new VentanaMenu("menu");
				vMenu.setVisible(true);
				VentanaLogin.this.setVisible(false);
				
			}

		});
		
		
	}
	
	
}
