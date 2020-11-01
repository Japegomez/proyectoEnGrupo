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
	private String nombreAsegurado;
	private String contraAsegurada;
	
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
		

		
		bLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				nombreAsegurado =  tfNombreUsuario.getText().replaceAll( "'", "''" );
				contraAsegurada = tfContrasenya.getText().replaceAll( "'", "''");
				if(!nombreAsegurado.isEmpty() && !contraAsegurada.isEmpty()) {
					if(BaseDatos.compruebaUsuario(nombreAsegurado)) {
						JOptionPane.showMessageDialog(VentanaLogin.this, "El usuario " + tfNombreUsuario.getText() + " no existe en la base de datos, primero debe registrarse.");
					}
					else {
						if(BaseDatos.compruebaContrasenya(nombreAsegurado, contraAsegurada)) VentanaLogin.this.dispose();
						else {
							JOptionPane.showMessageDialog(VentanaLogin.this, "contraseña incorrecta");
						}
						
					}
				
				}
				else {
					JOptionPane.showMessageDialog(VentanaLogin.this, "Rellene ambos campos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		bRegistro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				nombreAsegurado =  tfNombreUsuario.getText().replaceAll( "'", "''" );
				contraAsegurada = tfContrasenya.getText().replaceAll( "'", "''");
				if(!nombreAsegurado.isEmpty() && !contraAsegurada.isEmpty()) {
					if(BaseDatos.compruebaUsuario(nombreAsegurado)) {
						BaseDatos.registrarUsuario(nombreAsegurado, contraAsegurada);
						VentanaLogin.this.dispose();
					}
					else {
						JOptionPane.showMessageDialog(VentanaLogin.this, "el usuario " + tfNombreUsuario.getText() + " ya esta registrado.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(VentanaLogin.this, "Rellene ambos campos", "Error", JOptionPane.ERROR_MESSAGE);
				}
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
				Usuario usu = new Usuario(nombreAsegurado, contraAsegurada);
				VentanaMenu vMenu = new VentanaMenu("menu", usu);
				vMenu.setVisible(true);
				
			}

		});
		
		
		
	}
	
	
}
